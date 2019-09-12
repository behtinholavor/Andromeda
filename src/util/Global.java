package util;

public class Global {
	private static Global instance;
	public static Sessao sessao;
	public static Funcao funcao;
	
	public Global() {
		
	}
	
	public static Global getInstance() {
        if (instance == null)
            instance = new Global();        	
        return instance;
    }
	
	public static void setInstance(Global instance) {
		Global.instance = instance;
	}

}
