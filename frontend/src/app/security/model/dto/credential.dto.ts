import { DtoInterface } from '@shared/model';

export interface CredentialDto extends DtoInterface {
  actif: true;
  credential_id: number;
  username: string;
  password: string;
  firstname: string;
  lastname: string;
}
