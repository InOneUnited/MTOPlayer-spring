import {
  CanDeactivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  UrlTree
} from '@angular/router';
import { Component, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class UnsavedChangesGuard implements CanDeactivate<Component> {
  canDeactivate(
    component: any,
    currentRoute: ActivatedRouteSnapshot,
    currentState: RouterStateSnapshot,
    nextState?: RouterStateSnapshot
  ): boolean | UrlTree | Observable<boolean> | Promise<boolean> {
    return true;
  }
}
