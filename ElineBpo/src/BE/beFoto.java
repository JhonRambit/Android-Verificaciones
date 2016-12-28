package BE;

public class beFoto 
{
	// PROPIEDADES Y CONSTRUCTORES TIPO PERSONA ...!! 
		public String Nombre;
	 	public String Paterno;
		public String Materno;
		public String Razon_social;
	 	public String Formato;
	 	public String Foto_1;
	 	public String Foto_2;
	 	public String Foto_3;
	 	
		public beFoto(String nombre, String paterno, String materno, String razon_social, String formato,String foto_1, String foto_2, String foto_3)
		{
			this.Nombre=nombre;
			this.Paterno=paterno;
			this.Materno=materno;
			this.Razon_social=razon_social;
			this.Formato=formato;
			this.Foto_1=foto_1;
			this.Foto_1=foto_2;
			this.Foto_1=foto_3;
		}
		
		// CREAMOS EL CONSTRUCTOR VACIO ...!! 
		public beFoto() {}
			
		public String getNombre()
		{
			return Nombre;
		}	
		
		public String getPaterno()
		{
			return Paterno;
		}
		
		public String getMaterno()
		{
			return Materno;
		}
		
		public String getRazon_social()
		{
			return Razon_social;
		}
		
		public String getFormato()
		{
			return Formato;
		}
			
		public String getFoto_1()
		{
			return Foto_1;
		}
		
		public String getFoto_2()
		{
			return Foto_2;
		}
		
		public String getFoto_3()
		{
			return Foto_3;
		}
}
