import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {ChargingSpidComponent} from './charging-spid/charging-spid.component';
import {ChargingCieComponent} from './charging-cie/charging-cie.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'charging-spid/:idp',
    component: ChargingSpidComponent,
  },
  {
    path: 'charging-cie',
    component: ChargingCieComponent,
  },
  {
    path: '**',
    redirectTo: 'login',
    pathMatch: 'full',
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SpidRoutingModule {
}
