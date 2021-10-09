import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenuGeneralComponent } from './Componentes/Menus/menu-general/menu-general.component';
import { FooterComponent } from './Componentes/footer/footer.component';
import { MenuLectorComponent } from './Componentes/Menus/menu-lector/menu-lector.component';
import { MenuEditorComponent } from './Componentes/Menus/menu-editor/menu-editor.component';
import { IniciosesionComponent } from './Componentes/iniciosesion/iniciosesion.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { LoginService } from './Servicios/Sesion/Login.service';
import { HttpClientModule } from '@angular/common/http';
import { RedireccionarService } from './Servicios/Redireccionar.service';
import { MenuadminComponent } from './Componentes/Menus/menuadmin/menuadmin.component';
import { ErrorComponent } from './Componentes/Errores/error/error.component';

const rutas: Routes = [
  { path: 'InicioSesion', component: IniciosesionComponent },
  { path: 'InicioLector', component: MenuLectorComponent },
  { path: 'InicioEditor', component: MenuEditorComponent },
  { path: 'InicioAdmin', component: MenuadminComponent },
  { path: 'Inicio', component: MenuGeneralComponent }
]

@NgModule({
  declarations: [
    AppComponent,
    MenuGeneralComponent,
    FooterComponent,
    MenuLectorComponent,
    MenuEditorComponent,
    IniciosesionComponent,
    MenuadminComponent,
    ErrorComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(rutas)
  ],
  providers: [LoginService, RedireccionarService],
  bootstrap: [AppComponent]
})
export class AppModule { }
