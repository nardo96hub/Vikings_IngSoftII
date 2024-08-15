import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { ContenidoUsuarioService } from '../contenido-usuario.service';
import { Contacto } from '../model/contacto';
import { Turno } from '../model/turno';
import { TokenStorageService } from '../token-storage.service';
import { Producto } from '../model/producto';
import { TurnoService } from '../turno.service';
import { FormGroup, FormBuilder, Validators, AbstractControl } from '@angular/forms';
import { ProductoService } from '../producto.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-board-admin',
  templateUrl: './board-admin.component.html',
  styleUrls: ['./board-admin.component.css']
})
export class BoardAdminComponent implements OnInit {
  //listProveedores = ["Proveedor Bruno","Proveedor Diego","Proveedor Luciano"]
  content = '';
  filtro = '';
  tablas:any;

  turnos:Turno[];
  turnosDisponibles:Turno[];
  turnosNoDisponibles:Turno[];

  contactos:Contacto[];
  productos:Producto[];
  currentUser:any;
  privilegios:String[];

  esAdmin = false;
  esEmpleado = false;

  turnoActual:Turno;
  productoActual:Producto;
  productoNuevo:Producto;

  form: FormGroup;
  formEdit: FormGroup;

  turnoSol = {'click':true,'mensaje':'Ocultar Turnos Solicitados'}
  turnoNoSol = {'click':true,'mensaje':'Ocultar No Turnos Solicitados'}

  productoAgregado: boolean = false;
  listarProductos: boolean = false;
  precioCoste: boolean = false;

  valFormEd = {'nomReq':false,'descReq':false,'cosReq':false,'cantReq':false,'preReq':false,'proReq':false
    , 'preCosEdi':false, 'cantNeg':false,'preNeg':false,'cosNeg':false
  };

  valFormCr = {'nomReq':false,'descReq':false,'cosReq':false,'cantReq':false,'preReq':false,'proReq':false
    , 'preCosEdi':false, 'cantNeg':false,'preNeg':false,'cosNeg':false
  };

  confirmaEditaProd:boolean = false;

  modalEdit:boolean;
  modalCreate:boolean;

  /*
  nombreProductoInput='';
  descripcionInput='';
  precioInput=0;
  costeInput=0;
  cantidadInput=0;
  proveedorInput='';

  */
  constructor(private userService: ContenidoUsuarioService, private token: TokenStorageService, private fb:FormBuilder, private service:TurnoService, private prodService:ProductoService, private router: Router) { }

  ngOnInit() {
    this.modalEdit=true 
    this.modalCreate=true

    console.log("Edit:",this.modalEdit," create: ",this.modalCreate);
    
    this.tablas = {'turno':false,'mensaje':false,'producto':false}
    
    
    this.currentUser = this.token.getUser();
    if(this.currentUser!=null){
      this.privilegios = this.currentUser.roles;
      console.log("Privilegios: ",this.privilegios)
      this.esAdmin = (this.privilegios.includes('ROLE_ADMIN') || this.privilegios.includes('ROLE_EMPLEADO'));
      this.esEmpleado = this.privilegios.includes('ROLE_EMPLEADO');
    }

     this.userService.getAll().subscribe((turnos:Turno[])=>{
      console.log(turnos);
      
      this.turnos = turnos;
      this.turnosDisponibles = turnos.filter(t=>t.disponible)
      this.turnosNoDisponibles = turnos.filter(t=>t.disponible==false)
      this.turnosNoDisponibles = this.turnosNoDisponibles.filter(t=>!t.username.includes('admin'))
     
    })

    this.userService.getMensajes().subscribe((contactos:Contacto[])=>{
      this.contactos=contactos;
    })

    this.userService.getProductos().subscribe((productos:Producto[])=>{
      this.productos=productos;
      console.log(productos);
      
    })

    

    this.form = this.fb.group({
      nombre: [''],
      descripcion: [''],
      precio: [''],
      disponible: [true],
      coste: [''],
      cantidad: [''],
      proveedor: ['']
    });

    this.formEdit = this.fb.group({
      nombre: [''],
      descripcion: [''],
      precio: [''],
      disponible: [false],
      coste: [''],
      cantidad: [''],
      proveedor: ['']
    }

    );
  }



  precioValido(control: AbstractControl): { [key: string]: any } | null {
    const precio = control.value;
    if (precio !== null && precio < 0) {
      return { 'precioInvalido': true };
    }
    return null;
  }
  
  costeValido(control: AbstractControl): { [key: string]: any } | null {
    const coste = control.value;
    //const precio = this.form.get('precio').value;
    if (coste !== null && coste < 0) {
      return { 'costeInvalido': true };
    }
    return null;
  }

  validarFormCreate(fProdCre:Producto){
    this.valFormCr = {'nomReq':false,'descReq':false,'cosReq':false,'cantReq':false,'preReq':false,'proReq':false
      , 'preCosEdi':false, 'cantNeg':false,'preNeg':false,'cosNeg':false
    };

    if (!fProdCre.nombre) this.valFormCr.nomReq = true;
    if (!fProdCre.descripcion) this.valFormCr.descReq = true;
    if (!fProdCre.precio) this.valFormCr.preReq = true;
    if (!fProdCre.coste) this.valFormCr.cosReq = true;
    if (!fProdCre.cantidad) this.valFormCr.cantReq = true;
    if (!fProdCre.proveedor) this.valFormCr.proReq = true;
    if (fProdCre.precio < fProdCre.coste) this.valFormCr.preCosEdi = true 
    if (fProdCre.precio <0) this.valFormCr.preNeg = true
    if (fProdCre.coste <0) this.valFormCr.cosNeg = true
    if (fProdCre.cantidad < 0) this.valFormCr.cantNeg = true 

  //  this.precioCoste = false;
  /*
    const precio = this.form.get('precio').value;
    const coste = this.form.get('coste').value;
    
    this.precioCoste = precio >= coste;
  */ 
   
    this.modalCreate = false
  }

  salir(){

    this.router.navigate(['/home']);
   /* setTimeout(() => {
      window.location.reload();
    }, 1000);
    */
  }
  
  agregarProducto(datosFormulario: Producto){

    console.log("Quiero crear el objecto:",datosFormulario);
    this.productoAgregado = false
    
    /*
    if (datosFormulario.proveedor == "-"){
      
      datosFormulario.proveedor = this.listProveedores[Math.floor(Math.random()*this.listProveedores.length)]
    }
    */
    datosFormulario.imagen = "./assets/imagenes/new-product.jpg"
    this.prodService.save(datosFormulario).subscribe(()=>{
      this.userService.getProductos().subscribe(p=>{
        this.productos = p
      })
      console.log("Se agrego producto:",datosFormulario);
      this.form.get('nombre').setValue('')
      this.form.get('descripcion').setValue('')
      this.form.get('precio').setValue('')
      this.form.get('disponible').setValue('')
      this.form.get('coste').setValue('')
      this.form.get('cantidad').setValue('')
      this.form.get('proveedor').setValue('')
      
    })
    



    /*
    const modalElement = document.getElementById('exampleModal5');
    if (modalElement) {
      modalElement.classList.remove('show');
      modalElement.setAttribute('aria-hidden', 'true');
      modalElement.setAttribute('style', 'display: none');
    }
    const modalBackdrop = document.getElementsByClassName('modal-backdrop')[0];
    if (modalBackdrop) {
      document.body.removeChild(modalBackdrop);
    }
*/
    // Marcar el producto como agregado exitosamente
    this.productoAgregado = true;
    // Restablecer productoAgregado después de 3 segundos para ocultar la notificación
    


  /*  setTimeout(() => {
      window.location.reload();
    }, 200);
    */
  }

  listaProductos(){
    this.listarProductos = !this.listarProductos;

    if (this.productoAgregado) this.productoAgregado = !this.productoAgregado
  }

  borrarProducto(){
    //this.productoActual.disponible = false;
    this.prodService.borrar(this.productoActual).subscribe(()=>{
      console.log("Se borro producto");
      this.userService.getProductos().subscribe(p=>{
        this.productos = p
      })
    })
  }
  
  editarProducto(producto: string, desc: string, precio: number, coste: number, cantidad: number, proveedor: string){
    this.productoActual.nombre = producto;
    this.productoActual.descripcion = desc;
    this.productoActual.precio = precio;
    this.productoActual.coste = coste;
    this.productoActual.cantidad = cantidad;
    this.productoActual.proveedor = proveedor;
    this.prodService.save(this.productoActual).subscribe(()=>{
      console.log("Se va a editar datos productos");
    })
  }

  borrarTurno(){
    this.turnoActual.disponible = true;
    this.service.save(this.turnoActual).subscribe(()=>{
      console.log("Se borro producto");
    })
  }

  editarTurno(user:string,mail:string,disp:boolean){
    this.turnoActual.username = user;
    this.turnoActual.email = mail;
    this.turnoActual.disponible = disp;
    this.service.save(this.turnoActual).subscribe(()=>{
      console.log("Se edito Turno");
    })
  }
  
  abrirModal(turno:Turno){
    this.turnoActual=turno;

  }

  abrirModalProducto(producto:Producto,tipoModal:string){

    if (tipoModal == 'E'){
      this.modalEdit = true;
      this.formEdit.get('nombre').setValue(producto.nombre)
      this.formEdit.get('descripcion').setValue(producto.descripcion)
      this.formEdit.get('precio').setValue(producto.precio)
      this.formEdit.get('disponible').setValue(producto.disponible)
      this.formEdit.get('coste').setValue(producto.coste)
      this.formEdit.get('cantidad').setValue(producto.cantidad)
      this.formEdit.get('proveedor').setValue(producto.proveedor)
      this.formEdit.get('disponible').setValue(producto.disponible)
    }
    if (tipoModal == 'C'){
      this.modalCreate = true;
    }

    this.productoActual = producto;

  }

  ocultarParrafo(disp:boolean):boolean{
    return disp;
  }

  ocultarProducto(disp:boolean):boolean{
    return (disp && this.listarProductos);
  }

  limpiarInput(){
    this.form.reset();
  }

  abrirTabla(nombreTabla:string) {
    if(nombreTabla == 'Turnos'){ 
      this.tablas.mensaje = false 
      this.tablas.producto = false
      this.tablas.turno = !this.tablas.turno
    }
    if(nombreTabla == 'Mensajes'){
      this.tablas.producto = false
      this.tablas.turno = false 
      this.tablas.mensaje = !this.tablas.mensaje
    }
    if(nombreTabla == 'Productos'){
      this.tablas.turno = false 
      this.tablas.mensaje = false
      this.tablas.producto = !this.tablas.producto
    }
    console.log("Tablas: ",this.tablas)
    /*
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
    */
  }

  editarProd(prod:Producto){
    prod.id=this.productoActual.id
    console.log("Se va a intentar actualizar:",prod);
    
    this.prodService.update(prod).subscribe(data=>{
      console.log("Se edito :",data);
      this.userService.getProductos().subscribe(data=>{
        this.productos = data
      })
    })
    
    
    this.modalEdit = false

    this.formEdit.get('nombre').setValue('')
    this.formEdit.get('descripcion').setValue('')
    this.formEdit.get('precio').setValue('')
    this.formEdit.get('disponible').setValue('')
    this.formEdit.get('coste').setValue('')
    this.formEdit.get('cantidad').setValue('')
    this.formEdit.get('proveedor').setValue('')

    
    
    //window.location.reload();
  }

  validarEditar(editProd: Producto){
    editProd.id= this.productoActual.id
    console.log("Se va a editar: ",editProd);
    
    this.valFormEd = {'nomReq':false,'descReq':false,'cosReq':false,'cantReq':false,'preReq':false,'proReq':false
        , 'preCosEdi':false, 'cantNeg':false,'preNeg':false,'cosNeg':false
      };

    if (!editProd.nombre) this.valFormEd.nomReq = true;
    if (!editProd.descripcion) this.valFormEd.descReq = true;
    if (!editProd.precio) this.valFormEd.preReq = true;
    if (!editProd.coste) this.valFormEd.cosReq = true;
    if (!editProd.cantidad) this.valFormEd.cantReq = true;
    if (!editProd.proveedor) this.valFormEd.proReq = true;
    if (editProd.precio < editProd.coste) this.valFormEd.preCosEdi = true 
    if (editProd.precio <0) this.valFormEd.preNeg = true
    if (editProd.coste <0) this.valFormEd.cosNeg = true
    if (editProd.cantidad < 0) this.valFormEd.cantNeg = true 

    this.modalEdit = false;
  }
  cerrarModal(modal:string){
    if (modal == 'E'){
      this.modalEdit = !this.modalEdit
    } 
    if (modal == 'C') {
      this.modalCreate = !this.modalCreate
    }
  }
  abrirModalCreate(){
    this.modalCreate = true
  }

  mensajeSolicitado(isTurno:string){
    if (isTurno == 'TS'){
      if (this.turnoSol) this.turnoSol.mensaje = 'Ocultar  Turnos Solicitados'
      else this.turnoSol.mensaje = 'Ver  Turnos Solicitados'
      console.log(this.turnoSol)
      this.turnoSol.click = !this.turnoSol.click
    } else {
      if (this.turnoNoSol) this.turnoNoSol.mensaje = 'Ocultar Turnos No Pedidos'
      else this.turnoNoSol.mensaje = 'Ver Turnos No Pedidos'
      console.log(this.turnoNoSol)
      this.turnoNoSol.click = !this.turnoNoSol.click
    }


  }
  borrarMensaje(c:Contacto){
    this.userService.deleteMensajes(c).subscribe((data)=>{
      console.log(data)
      this.userService.getMensajes().subscribe(m=>this.contactos = m)
    })
  }

  filtroProducto(f:string){
    this.filtro = f
}

  getProductoFiltro(){
    if (this.filtro != ''){
      return this.productos.filter(p=>p.nombre.includes(this.filtro) || p.descripcion.includes(this.filtro) 
                          || p.proveedor.includes(this.filtro) || String(p.cantidad).includes(this.filtro) 
                          || String(p.precio).includes(this.filtro) || String(p.coste).includes(this.filtro))
  
    } else return this.productos
  }

}
