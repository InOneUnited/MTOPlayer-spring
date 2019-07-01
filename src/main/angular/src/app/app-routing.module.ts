import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './modules/shared/services/guards/auth.guard';
import { SettingsRoutes } from './modules/settings/settings-routing.module';
import { MusicRoutes } from './modules/music/music-routing.module';
import { UnsavedChangesGuard } from './modules/shared/services/guards/unsaved-changes.guard';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'library',
    pathMatch: 'full',
    canActivate: [AuthGuard]
  },
  {
    path: 'settings',
    children: SettingsRoutes,
    canActivate: [AuthGuard],
    canDeactivate: [UnsavedChangesGuard]
  },
  {
    path: 'music',
    children: MusicRoutes,
    canActivate: [AuthGuard],
    canDeactivate: [UnsavedChangesGuard]
  },
  {
    path: '**',
    redirectTo: 'music'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
