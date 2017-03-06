import java.util.ArrayList;

public interface FrameDecoder {
  /**
   * Returns zero or more fully buffered [[???]] by reading `bytes` as they
   * arrive and decoding any complete frames.
   *
   * @param bytes the input bytes containing zero or more frames.
   *
   * @return zero or more decoded frames.
   */
  public ArrayList<Message> readBytes(byte[] bytes);
}
