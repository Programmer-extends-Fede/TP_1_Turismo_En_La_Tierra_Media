package app;

import tierraMedia.TierraMedia;

public class AppTierraMedia {
	public static void main(String[] args) {
		TierraMedia.construirUsuarios();
		System.out.println(TierraMedia.getUsuarios());
	}
}
