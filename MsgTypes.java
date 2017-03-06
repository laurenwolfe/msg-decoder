public enum MsgTypes {
  PICK  ((byte) 0x01),
  DROP  ((byte) 0x02)
  ;

  private final byte typeCode;

  MsgTypes(byte typeCode) {
    this.typeCode = typeCode;
  }

  public byte getTypeByte(MsgTypes type) {
    return type.typeCode;
  }

  public static MsgTypes getType(byte testType) {
    //Current spec allows for 256 types as a max, so I consider this to be
    //sufficiently efficient as a check; additionally, no additional code
    // needed for error handling
    for(MsgTypes type : MsgTypes.values()) {
      if(type.typeCode == testType) {
        return type;
      }
    }

    throw new IllegalArgumentException("Specified message type does not exist");
  }
}