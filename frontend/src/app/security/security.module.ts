import {NgModule} from '@angular/core';
import {SecurityRoutingModule} from './security-routing.module';
import {SigninComponent, SignupComponent} from '@security/component';
import {ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    SigninComponent,
    SignupComponent
  ],
    imports: [
        SecurityRoutingModule,
        ReactiveFormsModule
    ],
  providers: []
})
export class SecurityModule {
}
