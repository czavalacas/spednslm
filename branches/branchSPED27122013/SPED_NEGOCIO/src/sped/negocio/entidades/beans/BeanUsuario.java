package sped.negocio.entidades.beans;

public class BeanUsuario implements java.io.Serializable{
    @SuppressWarnings("compatibility:-7716331137436807403")
    private static final long serialVersionUID = 1L;

    private String clave;
    private String dni;
    private String estadoUsuario;
    private byte[] foto;
    private Integer nidUsuario;
    private String nombres;
    private String nombre;
    private String apellidos;
    private String usuario;
    private BeanRol rol;
    private BeanSedeNivel sedeNivel;
    private BeanAreaAcademica areaAcademica;
    private BeanError error;
    private String areaAcYProf;
    

    public BeanUsuario(){}
    
    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClave() {
        return clave;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDni() {
        return dni;
    }

    public void setEstadoUsuario(String estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public String getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public byte[] getFoto() {
        return foto;
    }


    public void setNidUsuario(Integer nidUsuario) {
        this.nidUsuario = nidUsuario;
    }

    public Integer getNidUsuario() {
        return nidUsuario;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setRol(BeanRol rol) {
        this.rol = rol;
    }

    public BeanRol getRol() {
        return rol;
    }

    public void setSedeNivel(BeanSedeNivel sedeNivel) {
        this.sedeNivel = sedeNivel;
    }

    public BeanSedeNivel getSedeNivel() {
        return sedeNivel;
    }

    public void setAreaAcademica(BeanAreaAcademica areaAcademica) {
        this.areaAcademica = areaAcademica;
    }

    public BeanAreaAcademica getAreaAcademica() {
        return areaAcademica;
    }

    public void setError(BeanError error) {
        this.error = error;
    }

    public BeanError getError() {
        return error;
    }

    public void setAreaAcYProf(String areaAcYProf) {
        this.areaAcYProf = areaAcYProf;
    }

    public String getAreaAcYProf() {
        return areaAcYProf;
    }
}
