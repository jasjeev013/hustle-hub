import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ProfileComponent } from './components/profile/profile.component';

const routes: Routes = [
  {
    path: '',
    redirectTo:'home',
    pathMatch:'full',
  },{
    path:'home',
    component: HomeComponent
  },{
    path:'login',
    component: LoginComponent
  },{
    path:'register',
    component: RegisterComponent
  },{
    path:'dashboard',
    component: DashboardComponent
  },{
    path:'user',
    component: ProfileComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 

}
