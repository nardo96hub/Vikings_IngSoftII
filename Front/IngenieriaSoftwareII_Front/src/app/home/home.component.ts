import { Component, OnInit } from '@angular/core';
import { ContenidoUsuarioService } from '../contenido-usuario.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private userService: ContenidoUsuarioService) { }

  ngOnInit(): void {
    this.userService.getAllInfo().subscribe(data=>{
      if (data == "Se Agrego informacion") console.log("Se agrego info");
      else {console.log("Ya hay info en la bdd")}
      
    })
  }

}
