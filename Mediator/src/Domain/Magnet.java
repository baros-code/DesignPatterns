package Domain;

public class Magnet {

	 enum Activity{Enabled,Disabled};
	
	private Activity activity;

	public Magnet() {
		activity=Activity.Disabled;
	}

	public boolean isActivated() {
		return (activity == Activity.Enabled);
	}


	public void activate() {
		activity=Activity.Enabled;
	}
	public void deActivate() {
		activity=Activity.Disabled;
	}
	
	
}
