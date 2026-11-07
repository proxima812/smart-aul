import gzip,struct,io,json,sys
from pathlib import Path

def load(path):
    r=io.BytesIO(gzip.decompress(Path(path).read_bytes()))
    def number(fmt): return struct.unpack(">"+fmt,r.read(struct.calcsize(fmt)))[0]
    def text(): return r.read(number("H")).decode("utf-8")
    def value(t):
        if t in (1,2,3,4,5,6): return number({1:"b",2:"h",3:"i",4:"q",5:"f",6:"d"}[t])
        if t==7: return list(r.read(number("i")))
        if t==8: return text()
        if t==9:
            k=number("b");n=number("i");return [value(k) for _ in range(n)]
        if t==10:
            d={}
            while (k:=number("b"))!=0:
                name=text();d[name]=value(k)
            return d
        if t in (11,12): return [number("i" if t==11 else "q") for _ in range(number("i"))]
        raise ValueError(t)
    t=number("b");name=text();return {name:value(t)}

if __name__=="__main__": print(json.dumps(load(sys.argv[1]),ensure_ascii=False,indent=2))
