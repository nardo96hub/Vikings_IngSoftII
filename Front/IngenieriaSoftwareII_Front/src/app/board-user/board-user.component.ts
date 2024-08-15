import { Component, OnInit } from '@angular/core';
import { ContenidoUsuarioService } from '../contenido-usuario.service';
import { TokenStorageService } from '../token-storage.service';
import { TurnoService } from '../turno.service';
import { Turno } from '../model/turno';
import { PdfGeneratorService } from '../pdf-generator.service';

@Component({
  selector: 'app-board-user',
  templateUrl: './board-user.component.html',
  styleUrls: ['./board-user.component.css']
})
export class BoardUserComponent implements OnInit {
  currentUser: any;
  turnos:Turno[];
  turnoActual:Turno;

  constructor(private userService: ContenidoUsuarioService,private token: TokenStorageService, private service:TurnoService, private pdfGeneratorService: PdfGeneratorService) { }

  ngOnInit() {
    this.currentUser = this.token.getUser();
    this.service.getAll().subscribe((turnos:Turno[])=>{
      this.turnos=turnos;
    })
  }

  turnoDisponible(disp:boolean, user:string):boolean{
    return (this.currentUser.username == user && !this.currentUser.username.includes('admin') && !disp);
  }
  isSolicitoTurno(turnos:Turno[]):boolean{
    let turno = turnos.filter(t=>t.disponible==false && t.username==this.currentUser.username && !this.currentUser.username.includes('admin'))
    
    return turno.length>0
  }

  abrirModal(turno:Turno){
    this.turnoActual=turno;
  }

  borrarTurno(){
    this.turnoActual.disponible = true;
    this.service.save(this.turnoActual).subscribe(()=>{
      console.log("Se borro Turno");
    })
  }

  generarPDF(turn:Turno): void {
    this.pdfGeneratorService.generatePdf(turn);
    this.pdfGeneratorService.generatePdf2(turn);
  }

}
