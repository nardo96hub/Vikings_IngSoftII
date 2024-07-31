import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ContactoService } from '../contacto.service';
import { Contacto } from '../model/contacto';
import { TokenStorageService } from '../token-storage.service';

@Component({
  selector: 'app-contacto',
  templateUrl: './contacto.component.html',
  styleUrls: ['./contacto.component.css']
})
export class ContactoComponent implements OnInit {

  form:FormGroup;
  activado = false;

  constructor(private service:ContactoService, private fb:FormBuilder,private token:TokenStorageService) { }

  ngOnInit(): void {
    this.form=this.fb.group({
      nombre:['', Validators.required],
      apellido: '',
      email:['', Validators.email],
      telefono:['', Validators.required],
      comentario:['', Validators.required]
    })
    this.activado=false;
  }

  enviar(contacto:Contacto){
    this.service.save(contacto).subscribe(()=>{
      console.log("Envio de datos");
      this.activado=true;
    })
  }
  islogueado(){
    let user = this.token.getUser()
    if (user != null) return true;
    return false;
  }
}
