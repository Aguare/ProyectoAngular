import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenuGeneralComponent } from './Menus/menu-general/menu-general.component';
import { FooterComponent } from './footer/footer.component';
import { MenuLectorComponent } from './Menus/menu-lector/menu-lector.component';
import { MenuEditorComponent } from './Menus/menu-editor/menu-editor.component';
import { IniciosesionComponent } from './iniciosesion/iniciosesion.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { LoginService } from './Servicios/Sesion/Login.service';
import { HttpClientModule } from '@angular/common/http';
import { RedireccionarService } from './Servicios/Redireccionar.service';

const rutas: Routes = [
  { path: 'InicioSesion', component: IniciosesionComponent },
  { path: 'InicioLector', component: MenuLectorComponent },
  { path: 'InicioEditor', component: MenuEditorComponent }
]

@NgModule({
  declarations: [
    AppComponent,
    MenuGeneralComponent,
    FooterComponent,
    MenuLectorComponent,
    MenuEditorComponent,
    IniciosesionComponent,
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
