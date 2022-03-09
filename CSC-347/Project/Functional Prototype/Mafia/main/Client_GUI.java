package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Client_GUI extends JFrame {

	private static JPanel contentPane;
	private static JTextField msg_textField;
	private static JTextArea msg_area;
	private static JButton msgSend_button;
	
	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client_GUI frame = new Client_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		try {
			//here the ip address is local address bc we're running client and server on same server. use port 1201 bc it's open.
			s = new Socket("10.76.158.200", 1201);	
			//my external ip is 100.43.21.95, but this doesn't work for some reason
			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
			String msgin = "";
			while(!msgin.equals("exit")) {
				msgin = din.readUTF();
				msg_area.setText(msg_area.getText().trim() + "\nServer:\t" + msgin);
			}
		
		} catch (Exception ex) {
			
		}
		
		
	}

	/**
	 * Create the frame.
	 */
	public Client_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		msg_area = new JTextArea();
		msg_area.setBounds(10, 11, 416, 180);
		contentPane.add(msg_area);
		
		msg_textField = new JTextField();
		msg_textField.setBounds(10, 202, 320, 50);
		contentPane.add(msg_textField);
		msg_textField.setColumns(10);
		
		msgSend_button = new JButton("Send");
		msgSend_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("CLIENT SEND BUTTON PRESSED");
				try {
					String msgout = "";
					msgout = msg_textField.getText().trim();
					dout.writeUTF(msgout);
				} catch (Exception ex) {
					
				}
				
			}
		});
		msgSend_button.setBounds(340, 202, 89, 50);
		contentPane.add(msgSend_button);
	}

}
