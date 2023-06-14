import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PublicGuard, SecurityGuard } from '@security/guard';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'note',
    pathMatch: 'full',
  },
  {
    path: 'dashboard',
    canActivate: [SecurityGuard],
    loadChildren: () =>
      import('./modules/dashboard/dashboard.module').then(
        (m) => m.DashboardModule
      ),
  },
  {
    path: 'account',
    canActivate: [PublicGuard],
    loadChildren: () =>
      import('./security/security.module').then((m) => m.SecurityModule),
  },
  {
    path: 'note',
    canActivate: [SecurityGuard],
    loadChildren: () =>
      import('./modules/note/note.module').then((m) => m.NoteModule),
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
