#!/usr/bin/env groovy

def call(orig) {
     bos = new ByteArrayOutputStream()
     oos = new ObjectOutputStream(bos)
     oos.writeObject(orig); oos.flush()
     bin = new ByteArrayInputStream(bos.toByteArray())
     ois = new ObjectInputStream(bin)
     return ois.readObject()
}