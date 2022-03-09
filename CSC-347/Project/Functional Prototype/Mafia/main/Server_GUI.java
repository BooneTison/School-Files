package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.*;
import java.net.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Server_GUI extends JFrame {

	private static JPanel contentPane;
	private static JTextField msg_textField;
	private static JTextArea msg_area;
	private static JButton msgSend_button;
	
	static ServerSocket ss;
	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;
	
	
	
	
	

	/**
	 * Create the frame.
	 */
	public Server_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		msg_area = new JTextArea();
		msg_area.setBounds(10, 11, 416, 181);
		contentPane.add(msg_area);
		
		msg_textField = new JTextField();
		msg_textField.setBounds(10, 211, 312, 41);
		contentPane.add(msg_textField);
		msg_textField.setColumns(10);
		
		msgSend_button = new JButton("Send");
		msgSend_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String msgout = "";
					msgout = msg_textField.getText().trim();
					dout.writeUTF(msgout);	//sending the server's message to the client
				} catch(Exception ex) {
					
				}
				
			}
		});
		msgSend_button.setBounds(332, 211, 94, 41);
		contentPane.add(msgSend_button);
	}
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server_GUI frame = new Server_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
		String msgin = "";
		try {
			ss = new ServerSocket(1201);	//server starts at port number 1201
			s = ss.accept();				//server accepts the connection
			
			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
			
			while (!msgin.equals("exit")) {
				msgin = din.readUTF();
				msg_area.setText(msg_area.getText().trim() + "\n" + msgin);	//displaying messages from client
			}
		}catch(Exception e) {
			
		}
		
	}
}
