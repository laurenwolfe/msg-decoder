public class Pick extends Message {
  //private byte[] msgBytes;
  //private byte idx;

  public Pick(byte length) {
      super(length, MsgTypes.PICK);
  }

  byte addBytes(byte[] bytes, byte bufferIdx) {
    while(!isFilled()) {
        msgBytes[idx] = bytes[bufferIdx];
        idx++;
        bufferIdx++;
    }
    return bufferIdx;
  }
}
