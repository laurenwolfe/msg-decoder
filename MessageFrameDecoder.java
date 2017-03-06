import java.util.ArrayList;

public class MessageFrameDecoder implements FrameDecoder {
  private ArrayList<Message> messages;
  private byte byteArrayIdx;
  private Message currentMsg;

  public MessageFrameDecoder() {
    this.messages = new ArrayList<>();
    this.byteArrayIdx = 0;
    this.currentMsg = null;
  }

  public ArrayList<Message> readBytes(byte[] bytes) {
    while(byteArrayIdx + 1 < bytes.length) {
      byte msgLength, msgType;

      if(currentMsg == null) {
        //if starting from a new message, retrieve message length and type
        msgLength = bytes[byteArrayIdx];
        msgType = bytes[byteArrayIdx + 1];
        byteArrayIdx += 2;
      } else {
        //otherwise, acquire the metadata from the Message object
        msgLength = currentMsg.getMsgLength();
        msgType = currentMsg.getMsgType();
      }

      ProcessArray(msgType, msgLength);

      if(msgLength < 1) {
        throw new IllegalArgumentException("Message length must be a positive " +
            "number (given: " + msgLength + ")");
      }

    }
    byteArrayIdx = 0;
    currentMsg = null;
    return messages;
  }

  private void ProcessArray(byte[] bytes, byte msgType, byte msgLength) {
    MsgTypes type = MsgTypes.getType(msgType);

    if(type == MsgTypes.DROP) {
      messages.add(new Drop(msgLength));
      return;
    }

    if(type == MsgTypes.PICK) {
      if(currentMsg == null) {
        currentMsg = new Pick(msgLength);
      }

      byteArrayIdx = currentMsg.addBytes(bytes, byteArrayIdx);

      //Message is fully received, add to message list and reset temp Message object
      if(currentMsg.isFilled()) {
        messages.add(currentMsg);
        currentMsg = null;
      }
    }
  }
}