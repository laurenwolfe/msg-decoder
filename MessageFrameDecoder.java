import java.util.ArrayList;

public class MessageFrameDecoder implements FrameDecoder {
  private Message tempMsg;
  private ArrayList<Message> messages;
  private byte bufferIdx;

  public MessageFrameDecoder() {
    this.messages = new ArrayList<>();
    this.bufferIdx = 0;
    this.tempMsg = null;
  }

  public ArrayList<Message> readBytes(byte[] bytes) {

    while(bufferIdx + 1 < bytes.length) {
      //retrieve metadata bytes
      byte msgLength = bytes[bufferIdx];
      byte msgType = bytes[bufferIdx + 1];
      bufferIdx += 2;

      if(msgLength <= 0) {
        throw new IllegalArgumentException("Invalid byte length argument: " + msgLength);
      }

      //determine message type; see MsgTypes enum class
      MsgTypes currType = MsgTypes.getType(msgType);

      if(currType == MsgTypes.PICK) {
        if(tempMsg == null) {
          tempMsg = new Pick(msgLength);
        }

        //add new bytes from stream until array is filled
        bufferIdx = tempMsg.addBytes(bytes, bufferIdx);

        //if message transmission has completed, add msg to output list
        if(tempMsg.isFilled()) {
          messages.add(tempMsg);
          tempMsg = null;
        }
      } else if(currType == MsgTypes.DROP) {
        messages.add(new Drop(msgLength));
      }
    }
    return messages;
  }
}

