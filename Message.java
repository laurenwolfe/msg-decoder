abstract class Message {
  private final byte msgLength;
  private final MsgTypes msgType;
  protected byte[] msgBytes;
  protected byte idx;

  public Message(byte length, MsgTypes msgType) {
    this.msgLength = length;
    this.msgType = msgType;
    this.msgBytes = new byte[length];
    this.idx = 0;
  }

  byte getMsgLength() {
    return msgLength;
  }

  MsgTypes getMsgType() {
    return msgType;
  }

  byte addBytes(byte[] bytes, byte bufferIdx) {
    return bufferIdx;
  }

  /**
   * Tests whether the message has been fully received (according to its length)
   * @return true if message has been transmitted; false otherwise
   */
  boolean isFilled() {
    return (idx + 1) == msgBytes.length;
  }
}