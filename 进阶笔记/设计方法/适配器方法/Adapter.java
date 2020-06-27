package Try_sig.Fac;

public class Adapter {
	public static void main(String[] args) {
		AutoPlayer autoPlayer = new AutoPlayer();
		autoPlayer.play("mp3");
		autoPlayer.play("Mp4");
		autoPlayer.play("ppt");
		autoPlayer.play("vdat");
	}
}

class MP4Player implements AdvancedMediaPlayer{
	@Override
	public void playMP4() {
		System.out.println("MP4 is supported");
	}

	@Override
	public void playPPt() {}
}

class PPTPlayer implements AdvancedMediaPlayer{
	@Override
	public void playMP4() {}

	@Override
	public void playPPt() {
		System.out.println("PPT is supported");
	}
	
}


class MediaAdapter implements OrdinaryMediaPlayer{
	private AdvancedMediaPlayer advancedMediaPlayer ;
	
	@Override
	public void play(String format) {
		if(format.equalsIgnoreCase("MP4")) {
			advancedMediaPlayer = new MP4Player();
			advancedMediaPlayer.playMP4();
		}
		else {
			advancedMediaPlayer = new PPTPlayer();
			advancedMediaPlayer.playPPt();
		}	
	}
}

class AutoPlayer implements OrdinaryMediaPlayer{
	private MediaAdapter mediaAdapter;
	@Override
	public void play(String format) {
		if(format.equalsIgnoreCase("mp3"))
			System.out.println("mp3 is supported");
		else if(format.equalsIgnoreCase("MP4") 
				|| format.equalsIgnoreCase("PPT")){
			mediaAdapter = new MediaAdapter();
			mediaAdapter.play(format);
		}
		else {
			System.out.println("invaid format");
		}
	}
}


