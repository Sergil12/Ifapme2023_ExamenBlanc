import { DtoInterface } from '@shared/model';
import { CredentialDto } from '../dto';

export interface SignupResponse extends DtoInterface {
  user: CredentialDto;
}
