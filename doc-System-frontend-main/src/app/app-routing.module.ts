import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateUserComponent } from './landing-page/user/create-user/create-user.component';

import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [


  {path:'login', component: LoginComponent},
  {path:'registration', component: RegistrationComponent},
  {path:'home', component: HomeComponent},
  {path:'', redirectTo:'home', pathMatch:'full'},

  {
    path: 'landing-page',
    loadChildren: () => import('../app/landing-page/landing-page.module').then(m => m.LandingPageModule)
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
