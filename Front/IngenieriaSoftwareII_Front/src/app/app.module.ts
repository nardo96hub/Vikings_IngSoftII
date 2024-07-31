import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';

import { HomeComponent } from './home/home.component';
import { ContactoComponent } from './contacto/contacto.component';
import { NosotrosComponent } from './nosotros/nosotros.component';
import { ServiciosComponent } from './servicios/servicios.component';
import { ProductosComponent } from './productos/productos.component';
import { RedesComponent } from './redes/redes.component';
import { TituloComponent } from './titulo/titulo.component';
import { ProfileComponent } from './profile/profile.component';
import { SliderComponent } from './slider/slider.component';
import { SliderProductosComponent } from './slider-productos/slider-productos.component';
import { LoginComponent } from './login/login.component';
import { PieReservaComponent } from './pie-reserva/pie-reserva.component';
import { RegistroComponent } from './registro/registro.component';
import { BoardUserComponent } from './board-user/board-user.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import { TurnosComponent } from './turnos/turnos.component';


import { RouterModule, Routes } from '@angular/router';
import { authInterceptorProviders } from './auth.interceptor';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';



const rutas:Routes = [

  {
    path: 'contacto',
    component:ContactoComponent
  },
  {
    path: 'nosotros',
    component:NosotrosComponent
  },
  {
    path:'productos',
    component:ProductosComponent
  },
  {
    path:'servicios',
    component:ServiciosComponent
  },
  {
    path:'home',
    component:HomeComponent
  },
  {
    path:'login',
    component:LoginComponent
  },
  {
    path:'login/registro',
    component:RegistroComponent
  },
  {
    path:'registro',
    component:RegistroComponent
  },
  {
    path:'admin',
    component:BoardAdminComponent
  },
  {
    path:'board-user',
    component:BoardUserComponent
  },
  {
    path:'profile',
    component:ProfileComponent
  },
  {
    path:'turnos',
    component:TurnosComponent
  },
  {
    path:'./turnos',
    component:TurnosComponent
  },
  {
    path:'',
    redirectTo:'/home', pathMatch: 'full'
  }

]

@NgModule({
  declarations: [
    AppComponent,
    
    ContactoComponent,
    NosotrosComponent,
    ServiciosComponent,
    ProductosComponent,
    RedesComponent,
    TituloComponent,
    SliderComponent,
    HomeComponent,
    SliderProductosComponent,
    LoginComponent,
    PieReservaComponent,
    RegistroComponent,
    BoardAdminComponent,
    BoardUserComponent,
    ProfileComponent,
    TurnosComponent
   
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(rutas),
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    NgbModule,
    FontAwesomeModule
  ],
  exports: [RouterModule],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
