package marimon.db;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;


public class Main<T extends Location> implements Unchecked {

//  public static void main(String[] args) {
//    new Main<>().go("select * from locations;").stream().forEach(System.out::println);
//  }
//
//  public List<T> go(String sql) {
//
//    JdbcDataSource h2ds = new JdbcDataSource();
//    h2ds.setURL("jdbc:h2:mem:test;INIT=runscript from 'src/main/resources/create.sql'");
//    DataSource datasource = h2ds;
//
//    Function<Supplier<ResultSet>, List<T>> mapper = rs -> {
//      final ResultSet resultSet = rs.get();
//      Location l = new Location(unchecked(() -> resultSet.getInt("id")).get(), unchecked(() -> resultSet.getString("name")).get());
//      System.out.println(l);
//      return new ArrayList<>();
//    };
//
//
//    Function<DataSource,
//      Function<Supplier<Connection>,
//        BiFunction<Supplier<Statement>,
//          Function<Supplier<ResultSet>, List<T>>, List<T>>>> doAll =
//      ds -> conn -> (stmt, apply2) ->
//        apply2.apply(unchecked(() -> stmt.get().executeQuery(sql)));
//
//
//
//    final Function<Supplier<Connection>, BiFunction<Supplier<Statement>,
//      Function<Supplier<ResultSet>, List<T>>, List<T>>> withDS = doAll.apply(datasource);
//
//    final Supplier<Connection> conn = unchecked(datasource::getConnection);
//    final BiFunction<Supplier<Statement>,
//      Function<Supplier<ResultSet>, List<T>>, List<T>> withConn = withDS.apply(conn);
//
//    final Supplier<Statement> stmt = unchecked(() -> conn.get().createStatement());
//    return withConn.apply(stmt, mapper);
//
//  }
//
}

