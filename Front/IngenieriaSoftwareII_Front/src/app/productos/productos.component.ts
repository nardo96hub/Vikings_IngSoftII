import { Component, OnInit } from '@angular/core';
import { ContenidoUsuarioService } from '../contenido-usuario.service';
import { Producto } from '../model/producto';

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
  styleUrls: ['./productos.component.css']
})
export class ProductosComponent implements OnInit {

  listarProductos: boolean = false;
  productos:Producto[];
  primont:Producto[] = [];
  depreal:Producto[] = [];
  insight:Producto[] = [];

  constructor(private userService: ContenidoUsuarioService) { }

  ngOnInit(): void {


    this.userService.getProductos().subscribe((productos:Producto[])=>{
      this.productos=productos;
      this.getProductos(this.productos.filter(t=>t.disponible))
    })
  }
  dividirSubProductos(arr:Producto[],cantDiv:number){
    const result = [];
    for (let i = 0; i < arr.length; i += cantDiv) {
        result.push(arr.slice(i, i + cantDiv));
    }
    return result;
  }

  getDividirProductos(arr:Producto[]):[Producto[],Producto[],Producto[]]{
    const ar1:Producto[] = [];
    const ar2:Producto[] = [];
    const ar3:Producto[] = [];

    let partSize = Math.ceil(arr.length/3);

    for (let i=0;i<arr.length;i++){
        
        if (i< partSize)
            ar1.push(arr[i])
        else if (i< partSize *2)
            ar2.push(arr[i])
        else
            ar3.push(arr[i])

    } 
    return [ar1,ar2,ar3]
  }

  getProcesoProducto(array:Producto[] ){
    const [p1,p2,p3] = this.getDividirProductos(array)

    this.primont = this.dividirSubProductos(p1,3)
    this.depreal = this.dividirSubProductos(p2,3)
    this.insight = this.dividirSubProductos(p3,3)
  }
  getProductos(arrayProductos:Producto[]){
    this.getProcesoProducto(arrayProductos)
  }


  abrirTabla(evt:any, servNombres:string) {
    var i:number;
    var tablinks:any;
    var tabcontent = document.getElementsByClassName("tabcontent") as HTMLCollectionOf<HTMLElement>;
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks") as HTMLCollectionOf<HTMLElement>;
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(servNombres).style.display = "block";
    evt.currentTarget.className += " active";
  }

  listaProductos(){
    this.listarProductos = !this.listarProductos;
  }

  ocultarProducto(disp:boolean):boolean{
    return (disp && this.listarProductos);
  }

  filtro: string = ''; // Inicializa el filtro de texto
  productosDisponibles: boolean = true;

  filtroTabla(value: string): void {
    this.filtro = value; // Almacena el valor del filtro de texto ingresado por el usuario
    // Verifica si hay productos que coinciden con el filtro
    this.productosDisponibles = this.productos.some(p => 
      this.ocultarProducto(p.disponible) && p.nombre.toLowerCase().includes(this.filtro.toLowerCase())
    );
  }




}
