package Domain.Exceptions;

public class LiteralException extends Exception{
   private final int lineNr;
   private final int position;
   private final String file;
   private final String message;

   public LiteralException(int lineNr, int position, String message, String file) {
       super();
       this.lineNr = lineNr;
       this.position = position;
       this.message = message;
       this.file = file;
   }

   public String getMessage(){
       return message + " in file " + file + " in  line " + lineNr + ", at position " + position + ".";
   }
}
