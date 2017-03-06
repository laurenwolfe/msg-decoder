abstract class Message {
  private final byte msgLength;
  protected byte[] msgBytes;
  protected byte idx;

  public Message(byte length) {
    this.msgLength = length;
    this.msgBytes = new byte[length];
    this.idx = 0;
  }

  byte addBytes(byte[] bytes, byte bufferIdx) {
    return 0x0;
  }

  /**
   * Tests whether the message has been fully received (according to its length)
   * @return true if message has been transmitted; false otherwise
   */
  boolean isFilled() {
    return (idx + 1) == msgBytes.length;
  }
}