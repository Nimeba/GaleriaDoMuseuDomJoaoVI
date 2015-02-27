package model;

public class Obra {
	static Artista artista;

	public static Artista getArtista() {
		return artista;
	}

	public static void setArtista(Artista artista) {
		Obra.artista = artista;
	}
}
