package br.ufac.si.academico.testes;

import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;

import com.mysql.fabric.xmlrpc.base.Data;

import br.ufac.si.academico.entidades.Cliente;
import br.ufac.si.academico.entidades.Usuario;
import br.ufac.si.academico.entidades.Vendedor;
import br.ufac.si.academico.gerentes.UsuarioGerente;
public class UsuarioTeste {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		UsuarioGerente ug = new UsuarioGerente();
		
//		Usuario a = ug.recuperar(1);
//		System.out.println(a);
//		System.out.println(a.getFuncao());
//		
//		Date d = Calendar.getInstance().getTime();
//		d.setDayOfMonth(31);
//		d.setMonth(12);
//		d.setYear(2019);
		Vendedor v1 = new Vendedor("Mickael", "01642319279","mickaellabres@gmail.com","28/08/1999", "123456");
		Vendedor v2 = new Vendedor("teste", "05623559679","teste","11/11/1998", "123");
		Cliente c1 = new Cliente("Andrielle Lima", "12345455900","andrielle@gmail.com", "24/10/1999", "654321");
		Usuario u = new Usuario("Hulk", "12345678900","testejjj","24/08/2000", "123", null);
		//Usuario u1 = new Usuario("Andrielle Use", "12345455900","andrielle@gmail.com", null, "654321", null);
		Cliente c2 = new Cliente("lucas Lima", "12235455900","andriell22e@gmail.com", "24/10/1999", "654321");
		Cliente c3 = new Cliente("briuno Lima", "13234455900","andriel323le@gmail.com", "24/10/1999", "654321");
		Cliente c4 = new Cliente("ldks Lima", "12345453900","andriel2331le@gmail.com", "24/10/1999", "654321");

		ug.adicionar(v2);
		ug.adicionar(v1);
		ug.adicionar(c1);
		ug.adicionar(c2);
		

		
		//v1.setDataNascimento("55/55/1655");
		//ug.atualizar(v1);
		
		

		ug.recuperar(c1.getId());
		
		ug.encerrar();

		
	}
}
