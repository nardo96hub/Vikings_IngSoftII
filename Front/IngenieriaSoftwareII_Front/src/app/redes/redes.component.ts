import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../token-storage.service';

@Component({
  selector: 'app-redes',
  templateUrl: './redes.component.html',
  styleUrls: ['./redes.component.css']
})
export class RedesComponent implements OnInit {

  private roles: string[];
  isLoggedIn = false;
  showAdminBoard = false;
  username: string;
  clickeado: boolean = false;

  constructor(private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    console.log("Token:",this.tokenStorageService.getToken())
    console.log("Estoy logeado: ",this.isLoggedIn)
    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;

      this.showAdminBoard = (this.roles.includes('ROLE_ADMIN') || this.roles.includes('ROLE_EMPLEADO'));
      console.log("Admin?:",this.showAdminBoard);
      
      this.username = user.username;
    }
  }
  logout() {
    this.tokenStorageService.signOut();
    this.isLoggedIn = false
    window.location.reload();
  }
  clickear(){
    this.clickeado = !this.clickeado;
  }
}
