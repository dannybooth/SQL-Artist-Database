package ex11;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Connection c = null;
		try {
			//gets the sql database file
			c = DriverManager.getConnection("jdbc:sqlite:SQliteTest.db");
			
			Statement s = c.createStatement();
			//1. Write a program that prints out the name of each artist in the database
			System.out.println("1.prints out the name of each artist in the database");
			
			ResultSet artist = s.executeQuery("SELECT * FROM Artist;");
			
			//loops through all the artists in the database and prints out their name and ID
			while(artist.next()){
				System.out.println("This artist with the ID "+ artist.getString("ArtistID")+" name is "+artist.getString("Name"));
			}
			
			//2. Write a program that prints out how many albums there are (use an SQL COUNT)
			System.out.println("2.prints out how many albums there are (use an SQL COUNT");
			ResultSet al = s.executeQuery("select count(*) from Album;");
			
			System.out.println("There are "+ al.getInt("count(*)")+" albums");
			
			//3. Write a program that prints out all of the tracks in the database by a specific artist
			System.out.println("3.prints out all of the tracks in the database by a specific artist");
			
			ResultSet artists = s.executeQuery("SELECT * FROM Artist;");
			
			System.out.println("Enter an artist to search for:");
			
			//asks for user input
			//toFind is the artist we want to find
			String toFind = scanner.nextLine();
			
			//loops through every artist
			while(artists.next()){
				String find = artists.getString("Name");
				//once it finds the correct artist it goes through this if statement
				if(find.compareTo(toFind)==0)
				{
					String toFind2 = artists.getString("ArtistID");
					ResultSet album = s.executeQuery("SELECT * FROM Album;");
					//loops through every album
					while(album.next()){
						String find2 = album.getString("ArtistID");
						//once it finds the correct album it goes through this if statement
						if(find2.compareTo(toFind2)==0)
						{
							String toFind3 = album.getString("AlbumID");
							ResultSet track = s.executeQuery("SELECT * FROM Track;");
							//loops through every track
							while(track.next()){
								String find3 = track.getString("AlbumID");
								//once it finds the correct track it goes through this if statement
								if(find3.compareTo(toFind3)==0)
								{
									//prints the tracks Name, Track ID, Album ID, Media Type, and Genre
									System.out.println("Name: "+ track.getString("Name")+", Track ID: "+ track.getString("TrackID")+", Album ID: "+ track.getString("AlbumID")+", Media Type ID: "+ track.getString("MediaTypeID")+", Genre ID: "+ track.getString("GenreID"));
								}
						}
					}
				}
			}
			}
	}
		catch(SQLException se) {
			se.printStackTrace();
		}

}
}
