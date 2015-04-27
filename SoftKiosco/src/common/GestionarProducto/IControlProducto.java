package common.GestionarProducto;

import java.rmi.Remote;
import java.util.Vector;

import server.persistencia.ManipuladorPersistencia;
import server.persistencia.dominio.Producto;

import common.DTOs.ProductoDTO;

public interface IControlProducto extends Remote{

	public Long agregarProductoDTO(ProductoDTO pDTO)throws Exception;
	public void eliminarProductoDTO(Long id)throws Exception;
	public void modificarProducto(Long id,ProductoDTO modificado)throws Exception;
	public Vector obtenerProductos()throws Exception;
	public Vector obtenerProductosCodyNom(String cod,String nom)throws Exception;
	public Vector obtenerStockTodosProductos(String atOrden)throws Exception;
	public Vector obtenerStockProductosProveedor(Long idProv,String atOrden)throws Exception;
	public Vector obtenerProductosProveedor(String nombrProv)throws Exception;
	public boolean existeProductoCodigo(Long codigo) throws Exception;
	public ProductoDTO buscarProductoDTOId(Long id) throws Exception;
	public ProductoDTO buscarProductoDTOCodigo(Long cod) throws Exception;
	public Producto buscarProductoId(ManipuladorPersistencia mp,Long id) throws Exception;
	public boolean puedoEditar(ProductoDTO dto,ProductoDTO modificado)throws Exception;
	public boolean productoAsociado(Long id) throws Exception;
	
}
