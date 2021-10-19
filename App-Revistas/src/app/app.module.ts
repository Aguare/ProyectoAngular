import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
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
import { MenuGeneralComponent } from './Componentes/Menus/menu-general/menu-general.component';
import { SinConexionComponent } from './Componentes/Errores/sin-conexion/sin-conexion.component';
import { BuscarComponent } from './Componentes/Revistas/buscar/buscar.component';
import { VerRevistasComponent } from './Componentes/Revistas/ver-revistas/ver-revistas.component';
import { TarjetaRevistaComponent } from './Componentes/Revistas/tarjeta-revista/tarjeta-revista.component';
import { PublicarRevistaComponent } from './Componentes/Revistas/publicar-revista/publicar-revista.component';
import { CrearEtiquetaComponent } from './Componentes/SeleccionEtiquetas/CrearEtiqueta/CrearEtiqueta.component';
import { PublicacionesComponent } from './Componentes/CompEditor/publicaciones/publicaciones.component';
import { PerfilComponent } from './Componentes/Inicio/perfil/perfil.component';
import { VerRevistaComponent } from './Componentes/Revistas/ver-revista/ver-revista.component';

const rutas: Routes = [
  {
    path: 'InicioLector', component: MenuLectorComponent,
    children: [
      { path: 'VerRevistas', component: BuscarComponent },
      { path: 'Perfil/:nombreUsuario', component: PerfilComponent }
    ]
  },
  {
    path: 'InicioEditor', component: MenuEditorComponent,
    children: [
      { path: 'NuevaPublicacion', component: PublicarRevistaComponent },
      { path: 'Publicaciones', component: PublicacionesComponent }
    ]
  },
  {
    path: 'InicioAdmin', component: MenuadminComponent,
    children: [
      { path: 'RegistrarAnunciante', component: RegistrarAnuncianteComponent },
      { path: 'VerAnunciantes', component: VerAnunciantesComponent }
    ]
  },
  {
    path: 'Inicio', component: MenuGeneralComponent,
    children: [
      { path: 'InicioSesion', component: IniciosesionComponent },
      { path: 'RegistroUsuario', component: RegistroUsuarioComponent },
    ]
  },
  { path: 'Mensaje', component: MostrarInformacionComponent },
  { path: 'ErrorConexion', component: SinConexionComponent }
]

@NgModule({
  declarations: [
    AppComponent,
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
    VerAnunciantesComponent,
    MenuGeneralComponent,
    SinConexionComponent,
    BuscarComponent,
    VerRevistasComponent,
    TarjetaRevistaComponent,
    PublicarRevistaComponent,
    CrearEtiquetaComponent,
    PublicacionesComponent,
    PerfilComponent,
    VerRevistaComponent
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
