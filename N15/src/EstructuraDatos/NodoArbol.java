package EstructuraDatos;

import java.util.Comparator;

public class NodoArbol<T>
{
	
	private T elemento;
	
	private NodoArbol<T> nodoDerecha;
	
	private NodoArbol<T> nodoIzquierda;
	
	private NodoArbol<T> padre;
	
	private int estado;
	
	private ArbolAVL arbol;
	
	public NodoArbol(T nElemento, ArbolAVL arb)
	{
		elemento = nElemento;
		nodoDerecha = null;
		padre = null;
		nodoIzquierda = null;
		estado = 0;
		arbol =arb;
	}

	public T getElemento() {
		return elemento;
	}

	public void setElemento(T elemento) {
		this.elemento = elemento;
	}

	public NodoArbol<T> getNodoDerecha() {
		return nodoDerecha;
	}

	public void setNodoDerecha(NodoArbol<T> nodoDerecha) {
		this.nodoDerecha = nodoDerecha;
	}

	public NodoArbol<T> getNodoIzquierda() {
		return nodoIzquierda;
	}

	public void setNodoIzquierda(NodoArbol<T> nodoIzquierda) {
		this.nodoIzquierda = nodoIzquierda;
	}

	public NodoArbol<T> getPadre() {
		return padre;
	}

	public void setPadre(NodoArbol<T> padre) {
		this.padre = padre;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public void insertar(NodoArbol<T> p, NodoArbol<T> q, Comparator<NodoArbol<T>> comparador) 
	{
		// If compare node is smaller, continue with the left node
		// q menor que p
		if(comparador.compare(p, q)<0)
		{
			if(p.nodoIzquierda==null) 
			{
				p.setNodoIzquierda(q); 
				q.setPadre(p);

				// Node is inserted now, continue checking the balance
				//TODO recursiveBalance(p);
			} 
			else 
			{
				insertar(p.getNodoIzquierda(),q, comparador);
			}

		} 
		// q mayor que p
		else if(comparador.compare(p, q)>0)
		{
			if(p.nodoDerecha==null) 
			{
				p.setNodoDerecha(q);
				q.setPadre(p);

				// Node is inserted now, continue checking the balance
				//TODO recursiveBalance(p);
			} 
			else 
			{
				insertar(p.getNodoDerecha(),q,comparador);
			}
		} 
		else 
		{
			// do nothing: This node already exists
		}
	}
	
	public void balance(NodoArbol<T> actual) {
		  
		  // we do not use the balance in this class, but the store it anyway
		  calcularBalance(actual);
		  int balance = actual.getEstado();

		  // check the balance
		  if(balance==-2) 
		  {

			  if(height(actual.getNodoIzquierda().getNodoIzquierda())>=height(actual.getNodoIzquierda().getNodoDerecha()))
			  {
				  actual = rotateRight(actual);
			  }
			  else 
			  {
				  actual = doubleRotateLeftRight(actual);
			  }
		  } 
		  else if(balance==2)
		  {
			  if(height(actual.getNodoDerecha().getNodoDerecha())>=height(actual.getNodoDerecha().getNodoIzquierda()))
			  {
				  actual = rotateLeft(actual);
			  } 
			  else
			  {
				  actual = doubleRotateRightLeft(actual);
			  }
		  }

		  // we did not reach the root yet
		  if(actual.getPadre()!=null) 
		  {
			 balance(actual.getPadre());
		  }
		  else
		  {
			  arbol.setRaiz(actual);
			  System.out.println("------------Balance----------------");
		  }
	}

	private void calcularBalance(NodoArbol<T> actual) 
	{
		// TODO Auto-generated method stub
		
	}
	
	public void eliminar(NodoArbol<T> buscador,NodoArbol<T> buscado,Comparator<T> comparadorEliminacion) 
	{
		if(comparadorEliminacion.compare(buscado.getElemento(),buscador.getElemento())<0)  
		{
			eliminar(buscador.getNodoIzquierda(),buscado,comparadorEliminacion);
		} 
		else if(comparadorEliminacion.compare(buscado.getElemento(),buscador.getElemento())>0) 
		{
			eliminar(buscador.getNodoDerecha(),buscado,comparadorEliminacion);
		}
		else if(comparadorEliminacion.compare(buscado.getElemento(),buscador.getElemento())==0)
		{
			eliminarNodo(buscador);
		}
	}

	private void eliminarNodo(NodoArbol<T> buscador) 
	{
		NodoArbol<T> referencia = null;
		// at least one child of q, q will be removed directly
		if(buscador.getNodoDerecha()==null || buscador.getNodoIzquierda()==null) 
		{
			// the root is deleted
			if(buscador.getPadre()==null)
			{
				arbol.setRaiz(null);
				buscador=null;
			}
		} 
		else 
		{
			// q has two children --> will be replaced by successor
			r = successor(q);
			q.key = r.key;
		}

		AvlNode p;
		if(r.left!=null) {
			p = r.left;
		} else {
			p = r.right;
		}

		if(p!=null) {
			p.parent = r.parent;
		}

		if(r.parent==null) {
			this.root = p;
		} else {
			if(r==r.parent.left) {
				r.parent.left=p;
			} else {
				r.parent.right = p;
			}
			// balancing must be done until the root is reached.
			recursiveBalance(r.parent);
		}
		r = null;

	}
}
