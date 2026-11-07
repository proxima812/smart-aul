import os, socket, struct, sys, json, time
from pathlib import Path

def read_packet(s):
    def read(n):
        data=b""
        while len(data)<n:
            chunk=s.recv(n-len(data))
            if not chunk: raise ConnectionError("RCON closed")
            data+=chunk
        return data
    size=struct.unpack("<i",read(4))[0]
    packet=read(size)
    return struct.unpack("<ii",packet[:8]), packet[8:-2].decode("utf-8",errors="replace")

def send(s, ident, kind, text):
    data=struct.pack("<ii",ident,kind)+text.encode()+b"\0\0"
    s.sendall(struct.pack("<i",len(data))+data)

def command(text):
    with socket.create_connection(("127.0.0.1",25586),timeout=15) as s:
        send(s,1,3,os.environ["SMART_AUL_RCON_PASSWORD"])
        while True:
            (ident,kind),_=read_packet(s)
            if ident == -1: raise PermissionError("RCON auth failed")
            if kind==2: break
        send(s,2,2,text)
        (ident,kind),part=read_packet(s)
        parts=[part]
        s.settimeout(0.3)
        try:
            while True:
                (ident,kind),part=read_packet(s)
                if ident==2: parts.append(part)
        except (socket.timeout,ConnectionError):
            pass
        result="".join(parts)
    record={"time":time.time(),"command":text,"result":result}
    Path("run/logs").mkdir(parents=True, exist_ok=True)
    with Path("run/logs/development-runtime-results.jsonl").open("a",encoding="utf-8") as f:
        f.write(json.dumps(record,ensure_ascii=False)+"\n")
    return result

if __name__=="__main__":
    for text in sys.argv[1:]:
        print(text+"\n"+command(text))
