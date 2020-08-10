//package server;
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Vector;
//
//public class ChatRoom {
//
//	
//	
//	private Map<String, Vector<ServerThread> >serverThreads;
//	
//	
//	
//	public ChatRoom(int port) {
//		try {
//			System.out.println("Binding to port " + port);
//			ServerSocket ss = new ServerSocket(port);
//			System.out.println("Bound to port " + port);
//			serverThreads = new HashMap<String, Vector<ServerThread> >();
//			while(true) {
//				Socket s = ss.accept(); // blocking
//				System.out.println("Connection from: " + s.getInetAddress());
//				ServerThread st = new ServerThread(s, this);
//				serverThreads.add(st);
//			}
//		} catch (IOException ioe) {
//			System.out.println("ioe in ChatRoom constructor: " + ioe.getMessage());
//		}
//	}
//	
//	
//	
////	public void broadcast(gameMessage gm, ServerThread st) {
////		//if (message != null) {
////		if (gm != null) {
////			//System.out.println(message);
////			System.out.println(gm.getUsername() + ": " + cm.getMessage());
////			for(ServerThread threads : serverThreads) {
////				if (st != threads) {
////					//threads.sendMessage(message);
////					threads.sendMessage(gm.hitPlayer());
////				}
////			}
////		}
////	}
//	
//	public static void main(String [] args) {
//		ChatRoom cr = new ChatRoom(6789);
//	}
//}