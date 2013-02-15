package server;



public class Message {		//Format på msg: "SIGNAL/*/Chatroom/*/Usernavn/*/lang string"
	String from="";
	String chatroom="";
	String message="";
	String input="";
	String signal="";
	
	public Message(String input){
		this.input=input;
	}
	
	public void parseMessage(){
		int temp=0;
		boolean foundChatroom=false;
		boolean foundSignal=false;
		for(int i=0;i<input.length()-2;i++){
			
			if(input.substring(i,i+3).equals("/*/")) {
				
				if(foundChatroom && foundSignal){
					from=input.substring(temp,i);
					message=input.substring(i+3);
				}
				
				if(!foundChatroom && foundSignal){
					chatroom=input.substring(temp,i);
					foundChatroom=true;
					temp=(i+3);
					}
				
				if(!foundChatroom && !foundSignal){
					signal=input.substring(0,i);
					foundSignal=true;
					temp=i+3;
				}
				
				
				
				
			}
			
			
			}
		
		
	}
	/*public static void main(String[] args) {
		
		msg.parseMessage();
		System.out.println("Signal: "+ msg.signal);
		System.out.println("From: "+ msg.from);
		System.out.println("Chatroom: "+ msg.chatroom);
		System.out.println("Message: "+ msg.message);
		
	}*/
	

}
