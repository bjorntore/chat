/*package server;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;


public class TestClient extends JFrame{
	OutputStream os;
	ObjectOutputStream oos;
	private JTextField textField;
	private JTextField textField_1;
	private String name;
	public TestClient(String IP, int port, final String name) throws ClassNotFoundException{
		this.name=name;
		getContentPane().setBackground(new Color(154, 205, 50));
		getContentPane().setForeground(new Color(102, 205, 170));
		getContentPane().setLayout(null);
		setTitle("Client");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		textField = new JTextField();
		textField.setBounds(12, 32, 174, 22);
		getContentPane().add(textField);
		textField.setColumns(10);
		setSize(300,178);
		show();
		JButton btnNewButton = new JButton("Send");
		btnNewButton.setBounds(195, 31, 83, 25);
		getContentPane().add(btnNewButton);
		
		textField_1 = new JTextField();
		textField_1.setBounds(96, 61, 174, 22);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setEditable(false);
		JLabel lblInc = new JLabel("INC:");
		lblInc.setBounds(66, 64, 31, 16);
		getContentPane().add(lblInc);
		btnNewButton.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Message msg=new Message("Stian");
				msg.setSignal("SEND");
				msg.setchatroom("CHATROOM1");
				msg.setMessage(textField.getText());
				try {
					oos.writeObject(msg);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				textField.setText("");
				
			}
			
		});
		try{
			Socket socket= new Socket(IP,port);
			os = socket.getOutputStream();   
			oos = new ObjectOutputStream(os);   
			InputStream is = socket.getInputStream();   
			ObjectInputStream ois = new ObjectInputStream(is); 
			
			Message msg= new Message("Stian");
			msg.setSignal("JOIN");
			msg.setchatroom("CHATROOM1");
			
			oos.writeObject(msg);
			
			while(true){
				msg=(Message)ois.readObject();
				textField_1.setText(msg.message);
			}
		}
		catch(IOException ex){
			System.err.println(ex);
		}	
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		new TestClient("localhost", 10823,"Leif");
		
		
	}
}
*/