import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-servicios',
  templateUrl: './servicios.component.html',
  styleUrls: ['./servicios.component.css']
})
export class ServiciosComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    this.abrirTabla(null,'Combos');
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

  combo = [
    {corte:"Combo Java (Perfilado de barba + Corte clasico)",precio:"$3500"},
    {corte:"Combo Haskell (Corte clasico + Color)",precio:"$3450"},
    {corte:"Combo Assembler (Perfilado de barba + Corte Clasico + Color)",precio:"$3800"},
    {corte:"Combo Fortran (Corte clasico + Iluminacion)",precio:"$3600"},
    {corte:"Combo Cobol (Perfilado de barba + Corte clasico + Color + Masaje capilar)",precio:"$4500"},
    {corte:"Combo Vikings (Perfilado de barba + Corte clasico + Color + Bigote a maquina + Aseo de barba)",precio:"$5000"}
  ]
  cabello = [
    {corte:"Corte clasico",precio:"$1500"},
    {corte:"Corte tijeras",precio:"$1650"},
    {corte:"Corte maquina",precio:"$1800"},
    {corte:"Corte navaja",precio:"$2000"},
    {corte:"Corte niños y jubilados",precio:"$1000"},
    {corte:"Color",precio:"$2000"}
  ]
  barba = [
    {corte:"Perfilado de barba",precio:"$1500"},
    {corte:"Aseo de barba",precio:"$1950"},
    {corte:"Bigote a maquina",precio:"$1800"},
    {corte:"Bigote con navaja",precio:"$1600"},
    {corte:"Masaje de barba",precio:"$2500"},
    {corte:"Arreglo de barba con navaja",precio:"$2000"}
  ]

}
