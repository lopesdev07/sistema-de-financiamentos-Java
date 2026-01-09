    package service;

    import java.sql.SQLException;
    import java.util.List;

    /** Interface genérica para lidar com exceções capturadas, define a necessidade
     * das classes de serviço implementarem seus métodos de forma padronizada e
     * reutilizável.
     *
     * @param <T> Tipo de objeto a ser definido pela classe que estende essa interface.
     */
    public interface FinanciamentoService<T> {

        /** Método genérico para lidar com exceções ao cadastrar dados no repositório
         *
         * @param objeto Objeto genérico a ser definido na implementação.
         * @throws SQLException Caso haja erro genérico ao acessar o banco de dados.
         */
        void cadastrarFinanciamento(T objeto) throws SQLException;

        /** Método genérico para lidar com exceções ao listar dados no repositório
         *
         * @return Lista de objetos lidos (o tipo de objeto é definido na implementação).
         * @throws SQLException Caso haja erro genérico ao acessar o banco de dados.
         */
        List<T> listarDados() throws SQLException ;
}
