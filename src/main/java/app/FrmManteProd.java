package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import lombok.Builder.Default;
import model.Categoria;
import model.Producto;
import model.Proveedor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class FrmManteProd extends JFrame {

	private JPanel contentPane;

	private JTextArea txtSalida;
	private JTextField txtCodigo;
	private JComboBox cboCategorias;
	private JComboBox cboProveedores;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JTable tblSalida;
	
	DefaultTableModel modelo = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmManteProd() {
		setTitle("Mantenimiento de Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 818);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnRegistrar.setBounds(324, 29, 89, 23);
		contentPane.add(btnRegistrar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 414, 143);
		contentPane.add(scrollPane);

		txtSalida = new JTextArea();
		scrollPane.setViewportView(txtSalida);

		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(177, 322, 89, 23);
		contentPane.add(btnListado);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(122, 11, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		JLabel lblCodigo = new JLabel("Id. Producto :");
		lblCodigo.setBounds(10, 14, 102, 14);
		contentPane.add(lblCodigo);

		cboCategorias = new JComboBox();
		cboCategorias.setBounds(122, 70, 86, 22);
		contentPane.add(cboCategorias);

		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 74, 102, 14);
		contentPane.add(lblCategora);

		JLabel lblNomProducto = new JLabel("Nom. Producto :");
		lblNomProducto.setBounds(10, 45, 102, 14);
		contentPane.add(lblNomProducto);

		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(122, 42, 144, 20);
		contentPane.add(txtDescripcion);

		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(10, 106, 102, 14);
		contentPane.add(lblStock);

		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(122, 103, 77, 20);
		contentPane.add(txtStock);

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(10, 134, 102, 14);
		contentPane.add(lblPrecio);

		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(122, 131, 77, 20);
		contentPane.add(txtPrecio);

		JLabel lblProveedores = new JLabel("Proveedor:");
		lblProveedores.setBounds(230, 106, 102, 14);
		contentPane.add(lblProveedores);

		cboProveedores = new JComboBox();
		cboProveedores.setBounds(300, 104, 120, 22);
		contentPane.add(cboProveedores);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		btnBuscar.setBounds(324, 63, 89, 23);
		contentPane.add(btnBuscar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 365, 416, 378);
		contentPane.add(scrollPane_1);
		
		tblSalida = new JTable();
		tblSalida.setModel(modelo);
		modelo.addColumn("C??digo");
		modelo.addColumn("Producto");
		modelo.addColumn("Stock");
		modelo.addColumn("Precio");
		modelo.addColumn("Categor??a");
		modelo.addColumn("Proveedor");
		scrollPane_1.setViewportView(tblSalida);
		
		scrollPane_1.setViewportView(tblSalida);

		llenaCombo();
		llenaCombo2();
	}

	void llenaCombo() {//combo de categorias
		// establecer conexion
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
		
		// obtener un listado de lo que pasamos el combo
		List<Categoria> lstCategoria= em.createQuery("select p from Categoria p", Categoria.class).getResultList();
		
		// recorrer el listado y agregarlo al combo cboxx.addItem("yyyy")
		cboCategorias.addItem("Seleccione...");
		for (Categoria c : lstCategoria) {
			cboCategorias.addItem(c.getDescripcion());
		}
		
		// cerrar conexxi??n
		em.close();
		
	}

	void llenaCombo2() {// combo de proveedores
		
		// establecer conexion
				EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
				EntityManager em = fabrica.createEntityManager();
				
				// obtener un listado de lo que pasamos el combo
				List<Proveedor> lstProveedor= em.createQuery("select p from Proveedor p", Proveedor.class).getResultList();
				
				// recorrer el listado y agregarlo al combo cboxx.addItem("yyyy")
				cboProveedores.addItem("Seleccione...");
				for (Proveedor p : lstProveedor) {
					cboProveedores.addItem(p.getNombre_rs());
				}
				
				// cerrar conexxi??n
				em.close();
		
	}

	void registrar() {
		// lee los campos
		String codigo = leerCodigo();
		String nombre = txtDescripcion.getText();
		int categoria = cboCategorias.getSelectedIndex();
		int stock = Integer.parseInt(txtStock.getText());
		int proveedor = cboProveedores.getSelectedIndex();
		double precio = Double.parseDouble(txtPrecio.getText());
		
		//crear un nuevo obj --> Producto
		Producto p = new Producto();
		p.setId_prod(codigo);
		p.setDes_prod(nombre);
		p.setStk_prod(stock);
		p.setIdcategoria(categoria);
		p.setIdproveedor(proveedor);
		p.setPre_prod(precio);
		p.setEst_prod(true); //valor "por default" como producto activo o disponible
		
		
		//guardar el nuevo objeto en la tabla
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
						
			aviso("Producto " + p.getDes_prod() + "registrado", "Aviso del sistema", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			
			aviso("Producto " + p.getDes_prod() + "No registrado\n" + e.getMessage(),"Error en el proceso", JOptionPane.ERROR_MESSAGE);

		}
		
		em.close();
	}

	private String leerCodigo() {
		// validaciones del campo
		if(txtCodigo.getText().isEmpty()) {
			aviso("Debe ingresar un valor en el c??digo", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
		
			return null; //valor de control
			
		}
		return txtCodigo.getText();
	}
	
	void aviso(String s, String titulo, int icono) {
		JOptionPane.showMessageDialog(this, s, titulo, icono);
	}

	void listado() {
		// obtener la conexion
				EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
				EntityManager em = fabrica.createEntityManager();
				
				
				List<Producto> lstProducto= em.createQuery("select p from Producto p", Producto.class).getResultList();
				
				System.out.println("Listado");
				for (Producto p : lstProducto) {
					imprimir("C??digo...: " + p.getId_prod());
					imprimir("Nombre...: " + p.getDes_prod());
					imprimir("Stock...: " + p.getStk_prod());
					imprimir("Precio...: " + p.getPre_prod());
					imprimir("Categoria.....: " + p.getIdcategoria() + "-" + p.getObjCategoria().getDescripcion());
					imprimir("Proveedor.....: " + p.getIdproveedor() + "-" + p.getObjProveedor().getNombre_rs());

					imprimir("-----------------------------------");
					
					// mostrr ne la tabla
					Object datos[] = {p.getId_prod(), p.getDes_prod(), p.getStk_prod(), p.getPre_prod(),
							          p.getIdcategoria() + "-" + p.getObjCategoria().getDescripcion(),
							          p.getIdproveedor() + "-" + p.getObjProveedor().getNombre_rs()};
					
					//agregar en la tabla 
					modelo.addRow(datos);
					
					
				}
						
						
				//cerrar
				em.close();
		
	}
	
	void imprimir(String s) {
		
		txtSalida.append(s + "\n");
	}
	
	void buscar() {
		// leer el c??digo
		String codigo = leerCodigo();
		
		// obtener un obj Producto seg??n el c??digo ingresado
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
		
		//Producto p = em.createQuery("select p from Producto p where...", Producto.class).getSingleResult();
		Producto p = em.find(Producto.class, codigo); // solo busca por ID
		if(p == null) {
			aviso("C??digo No existe", "Aviso del sistema", JOptionPane.WARNING_MESSAGE);
		} else {
			txtDescripcion.setText(p.getDes_prod());
			txtStock.setText(p.getStk_prod()+"");
		}
		em.close();
		
		// mostrar en los campos la informaci??n si existe, sino muestra un mensaje
		
	}
}
