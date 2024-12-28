import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: '/spid/login',
    pathMatch: 'full',
  },
  {
    path: 'spid',
    loadChildren: () => import('./page/spid/spid.module').then(m => m.SpidModule),
  }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}


