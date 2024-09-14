package dh.backend.clinica;

class OdontologoServiceTest {
    /*
    private static final Logger logger = LoggerFactory.getLogger(OdontologoServiceTest.class);
    private static OdontologoService odontologoService = new OdontologoService(new DaoH2Odontologo());

    @BeforeAll
    static void crearTabla() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./examen;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Test
    @DisplayName("Testear que se agregue un odontologo de manera correcta")
    void caso1(){
        Odontologo odontologo = new Odontologo("123", "Ricardo", "Cuellar");
        Odontologo odontologoDesdeDb = odontologoService.guardarOdontologo(odontologo);
        assertNotNull(odontologoDesdeDb);
    }

    @Test
    @DisplayName("Testear que se listen todos los odontologos")
    void caso2(){
        //DADO
        List<Odontologo> odontologoList;
        //CUANDO
        odontologoList = odontologoService.listaTodos();
        // entonces
        assertNotNull(odontologoList);
    }

    @Test
    @DisplayName("Testear que un odontologo pueda acceder por id")
    void caso3(){
        //Dado
        Integer id = 1;
        //cuando
        Odontologo odontologoDesdeDB = odontologoService.buscarPorId(id);
        // entonces
        assertEquals(id, odontologoDesdeDB.getId());
    }
    */


}