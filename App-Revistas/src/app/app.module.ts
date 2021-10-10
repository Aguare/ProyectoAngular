import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenuGeneralComponent } from './Componentes/Menus/menu-general/menu-general.component';
import { FooterComponent } from './Componentes/footer/footer.component';
import { MenuLectorComponent } from './Componentes/Menus/menu-lector/menu-lector.component';
import { MenuEditorComponent } from './Componentes/Menus/menu-editor/menu-editor.component';
import { IniciosesionComponent } from './Componentes/Inicio/iniciosesion/iniciosesion.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { LoginService } from './Servicios/Sesion/Login.service';
import { HttpClientModule } from '@angular/common/http';
import { RedireccionarService } from './Servicios/Redireccionar.service';
import { MenuadminComponent } from './Componentes/Menus/menuadmin/menuadmin.component';
import { ErrorComponent } from './Componentes/Errores/error/error.component';
import { CerrarSesionComponent } from './Componentes/Menus/cerrar-sesion/cerrar-sesion.component';
import { RegistroUsuarioComponent } from './Componentes/Inicio/registro-usuario/registro-usuario.component';
import { SeleccionEtiquetasComponent } from './Componentes/SeleccionEtiquetas/seleccion-etiquetas/seleccion-etiquetas.component';
import { EtiquetaComponent } from './Componentes/SeleccionEtiquetas/etiqueta/etiqueta.component';
import { MostrarInformacionComponent } from './Componentes/Errores/mostrar-informacion/mostrar-informacion.component';
import { RegistrarAnuncianteComponent } from './Componentes/CompAdmin/registrar-anunciante/registrar-anunciante.component';
import { VerAnunciantesComponent } from './Componentes/CompAdmin/ver-anunciantes/ver-anunciantes.component';

const rutas: Routes = [
  { path: 'InicioSesion', component: IniciosesionComponent },
  { path: 'InicioLector', component: MenuLectorComponent },
  { path: 'InicioEditor', component: MenuEditorComponent },
  {
    path: 'InicioAdmin', component: MenuadminComponent,
    children: [
      { path: 'RegistrarAnunciante', component: RegistrarAnuncianteComponent },
      { path: 'VerAnunciantes', component: VerAnunciantesComponent }
    ]
  },
  { path: 'Inicio', component: MenuGeneralComponent },
  { path: 'RegistroUsuario', component: RegistroUsuarioComponent },
  { path: 'Mensaje', component: MostrarInformacionComponent }
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
    CerrarSesionComponent,
    RegistroUsuarioComponent,
    SeleccionEtiquetasComponent,
    EtiquetaComponent,
    MostrarInformacionComponent,
    RegistrarAnuncianteComponent,
    VerAnunciantesComponent
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
