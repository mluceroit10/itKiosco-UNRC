-- MySQL dump 9.09
--
-- Host: 169.254.109.17    Database: softkioscodb
---------------------------------------------------------
-- Server version	4.0.15-nt

--
-- Table structure for table `jdo_table`
--

DROP TABLE IF EXISTS jdo_table;
CREATE TABLE jdo_table (
  TABLE_ID int(11) NOT NULL default '0',
  NEXT_ID int(11) NOT NULL default '0',
  JAVA_NAME varchar(128) binary NOT NULL default '',
  TABLE_NAME varchar(64) binary NOT NULL default '',
  PRIMARY KEY  (TABLE_ID),
  UNIQUE KEY JAVA_NAME (JAVA_NAME),
  UNIQUE KEY TABLE_NAME (TABLE_NAME)
) TYPE=InnoDB;

--
-- Dumping data for table `jdo_table`
--


/*!40000 ALTER TABLE jdo_table DISABLE KEYS */;
LOCK TABLES jdo_table WRITE;
INSERT INTO jdo_table VALUES (1,1,'server.persistencia.dominio.Oid','OID'),(2,1,'server.persistencia.dominio.Localidad','LOCALIDAD'),(3,1,'server.persistencia.dominio.Provincia','PROVINCIA'),(4,1,'server.persistencia.dominio.Kiosco','KIOSCO'),(5,1,'server.persistencia.dominio.BecaAsignada','BECA_ASIGNADA'),(6,2,'server.persistencia.dominio.Usuario','USUARIO'),(7,0,'server.persistencia.dominio.Producto','PRODUCTO'),(8,0,'server.persistencia.dominio.Proveedor','PROVEEDOR'),(9,0,'server.persistencia.dominio.ItemFactura','ITEM_FACTURA'),(10,0,'server.persistencia.dominio.Factura','FACTURA'),(11,0,'server.persistencia.dominio.FacturaProveedor','FACTURA_PROVEEDOR'),(12,0,'server.persistencia.dominio.MovimientoCaja','MOVIMIENTO_CAJA'),(13,0,'server.persistencia.dominio.UsuarioPlanillaES','USUARIO_PLANILLA_ES'),(14,0,'server.persistencia.dominio.PlanillaES','PLANILLA_ES'),(15,0,'server.persistencia.dominio.FacturaCliente','FACTURA_CLIENTE'),(16,0,'server.persistencia.dominio.Sesion','SESION');
UNLOCK TABLES;
/*!40000 ALTER TABLE jdo_table ENABLE KEYS */;

--
-- Table structure for table `oid`
--

DROP TABLE IF EXISTS oid;
CREATE TABLE oid (
  OID_ID bigint(20) NOT NULL default '0',
  OID bigint(20) default NULL,
  PRIMARY KEY  (OID_ID)
) TYPE=InnoDB;

--
-- Dumping data for table `oid`
--


/*!40000 ALTER TABLE oid DISABLE KEYS */;
LOCK TABLES oid WRITE;
INSERT INTO oid VALUES (35184372088832,25);
UNLOCK TABLES;
/*!40000 ALTER TABLE oid ENABLE KEYS */;

--
-- Table structure for table `provincia`
--

DROP TABLE IF EXISTS provincia;
CREATE TABLE provincia (
  PROVINCIA_ID bigint(20) NOT NULL default '0',
  ID bigint(20) default NULL,
  NOMBRE varchar(20) binary default NULL,
  PRIMARY KEY  (PROVINCIA_ID)
) TYPE=InnoDB;

--
-- Dumping data for table `provincia`
--


/*!40000 ALTER TABLE provincia DISABLE KEYS */;
LOCK TABLES provincia WRITE;
INSERT INTO provincia VALUES (105553116266496,131073,'Córdoba');
UNLOCK TABLES;
/*!40000 ALTER TABLE provincia ENABLE KEYS */;

--
-- Table structure for table `localidad`
--

DROP TABLE IF EXISTS localidad;
CREATE TABLE localidad (
  LOCALIDAD_ID bigint(20) NOT NULL default '0',
  COD_POSTAL varchar(255) binary default NULL,
  ID bigint(20) default NULL,
  NOMBRE varchar(255) binary default NULL,
  PROVINCIA_ID bigint(20) default NULL,
  PRIMARY KEY  (LOCALIDAD_ID),
  KEY LOCALIDAD_N1 (PROVINCIA_ID),
  CONSTRAINT `0_31` FOREIGN KEY (`PROVINCIA_ID`) REFERENCES `provincia` (`PROVINCIA_ID`)
) TYPE=InnoDB;

--
-- Dumping data for table `localidad`
--


/*!40000 ALTER TABLE localidad DISABLE KEYS */;
LOCK TABLES localidad WRITE;
INSERT INTO localidad VALUES (70368744177664,'5800',131075,'Río Cuarto',105553116266496);
UNLOCK TABLES;
/*!40000 ALTER TABLE localidad ENABLE KEYS */;

--
-- Table structure for table `kiosco`
--

DROP TABLE IF EXISTS kiosco;
CREATE TABLE kiosco (
  KIOSCO_ID bigint(20) NOT NULL default '0',
  DIRECCION varchar(255) binary default NULL,
  ENCARGADO varchar(255) binary default NULL,
  ID bigint(20) default NULL,
  INICIO_ACT date default NULL,
  LOCALIDAD_ID bigint(20) default NULL,
  NOMBRE varchar(255) binary default NULL,
  TELEFONO varchar(255) binary default NULL,
  PRIMARY KEY  (KIOSCO_ID),
  KEY KIOSCO_N1 (LOCALIDAD_ID),
  CONSTRAINT `0_37` FOREIGN KEY (`LOCALIDAD_ID`) REFERENCES `localidad` (`LOCALIDAD_ID`)
) TYPE=InnoDB;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS proveedor;
CREATE TABLE proveedor (
  PROVEEDOR_ID bigint(20) NOT NULL default '0',
  CODIGO bigint(20) default NULL,
  DIRECCION varchar(255) binary default NULL,
  ID bigint(20) default NULL,
  LOCALIDAD_ID bigint(20) default NULL,
  NOMBRE varchar(255) binary default NULL,
  TELEFONO varchar(255) binary default NULL,
  ELIMINADO tinyint(1) NOT NULL default '0',
  PRIMARY KEY  (PROVEEDOR_ID),
  KEY PROVEEDOR_N1 (LOCALIDAD_ID),
  CONSTRAINT `0_41` FOREIGN KEY (`LOCALIDAD_ID`) REFERENCES `localidad` (`LOCALIDAD_ID`)
) TYPE=InnoDB;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS usuario;
CREATE TABLE usuario (
  USUARIO_ID bigint(20) NOT NULL default '0',
  APELLIDO varchar(255) binary default NULL,
  CONTRASENIA varchar(255) binary default NULL,
  DIRECCION varchar(255) binary default NULL,
  DNI int(11) NOT NULL default '0',
  ID bigint(20) default NULL,
  IMPORTE_BECA double NOT NULL default '0',
  LOCALIDAD_ID bigint(20) default NULL,
  NOMB_USUARIO varchar(255) binary default NULL,
  NOMBRE varchar(255) binary default NULL,
  TELEFONO varchar(255) binary default NULL,
  TIPO_USUARIO varchar(255) binary default NULL,
  ELIMINADO tinyint(1) NOT NULL default '0',
  PRIMARY KEY  (USUARIO_ID),
  KEY USUARIO_N1 (LOCALIDAD_ID),
  CONSTRAINT `0_33` FOREIGN KEY (`LOCALIDAD_ID`) REFERENCES `localidad` (`LOCALIDAD_ID`)
) TYPE=InnoDB;


--
-- Dumping data for table `usuario`
--


/*!40000 ALTER TABLE usuario DISABLE KEYS */;
LOCK TABLES usuario WRITE;
INSERT INTO usuario VALUES (211106232532992,'Administrador','165-220-124-14-224-55-184-159','...',12345678,131079,0,70368744177664,'admin','...','','Administrador',0);
UNLOCK TABLES;
/*!40000 ALTER TABLE usuario ENABLE KEYS */;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS producto;
CREATE TABLE producto (
  PRODUCTO_ID bigint(20) NOT NULL default '0',
  CODIGO bigint(20) default NULL,
  ID bigint(20) default NULL,
  MARGEN_GANANCIA int(11) NOT NULL default '0',
  NOMBRE varchar(255) binary default NULL,
  PRECIO_ENTRADA double NOT NULL default '0',
  PRECIO_VENTA double NOT NULL default '0',
  PROVEEDOR_ID bigint(20) default NULL,
  STOCK_ACTUAL int(11) NOT NULL default '0',
  STOCK_MINIMO int(11) NOT NULL default '0',
  ELIMINADO tinyint(1) NOT NULL default '0',
  PRIMARY KEY  (PRODUCTO_ID),
  KEY PRODUCTO_N1 (PROVEEDOR_ID),
  CONSTRAINT `0_43` FOREIGN KEY (`PROVEEDOR_ID`) REFERENCES `proveedor` (`PROVEEDOR_ID`)
) TYPE=InnoDB;

--
-- Dumping data for table `producto`
--

--
-- Table structure for table `planilla_es`
--

DROP TABLE IF EXISTS planilla_es;
CREATE TABLE planilla_es (
  PLANILLA_ES_ID bigint(20) NOT NULL default '0',
  FECHA_P datetime default NULL,
  ID bigint(20) default NULL,
  INGRESO_CAJA_DIARIA double NOT NULL default '0',
  NRO_PLANILLA int(11) NOT NULL default '0',
  SALDO_CAJA_DIARIA double NOT NULL default '0',
  SALDO_CAJA_GENERAL double NOT NULL default '0',
  PERIODO varchar(255) binary default NULL,
  EN_CAJA double NOT NULL default '0',
  RETIRO double NOT NULL default '0',
  PRIMARY KEY  (PLANILLA_ES_ID)
) TYPE=InnoDB;

--
-- Table structure for table `factura`
--

DROP TABLE IF EXISTS factura;
CREATE TABLE factura (
  FACTURA_ID bigint(20) NOT NULL default '0',
  FECHA_HORA datetime default NULL,
  ID bigint(20) default NULL,
  IMPORTE_TOTAL double NOT NULL default '0',
  NRO_FACTURA bigint(20) default NULL,
  TIPO_FACTURA varchar(255) binary default NULL,
  PERIODO varchar(255) binary default NULL,
  PRIMARY KEY  (FACTURA_ID)
) TYPE=InnoDB;


--
-- Table structure for table `factura_cliente`
--

DROP TABLE IF EXISTS factura_cliente;
CREATE TABLE factura_cliente (
  FACTURA_CLIENTE_ID bigint(20) NOT NULL default '0',
  A_CARGO_BECARIO tinyint(1) NOT NULL default '0',
  BECARIO_ID bigint(20) default NULL,
  PLANILLA_ID bigint(20) default NULL,
  PRIMARY KEY  (FACTURA_CLIENTE_ID),
  KEY FACTURA_CLIENTE_N1 (PLANILLA_ID),
  KEY FACTURA_CLIENTE_N2 (BECARIO_ID),
  CONSTRAINT `0_61` FOREIGN KEY (`BECARIO_ID`) REFERENCES `usuario` (`USUARIO_ID`),
  CONSTRAINT `0_63` FOREIGN KEY (`FACTURA_CLIENTE_ID`) REFERENCES `factura` (`FACTURA_ID`),
  CONSTRAINT `0_65` FOREIGN KEY (`PLANILLA_ID`) REFERENCES `planilla_es` (`PLANILLA_ES_ID`)
) TYPE=InnoDB;


--
-- Table structure for table `factura_proveedor`
--

DROP TABLE IF EXISTS factura_proveedor;
CREATE TABLE factura_proveedor (
  FACTURA_PROVEEDOR_ID bigint(20) NOT NULL default '0',
  PROVEEDOR_ID bigint(20) default NULL,
  PRIMARY KEY  (FACTURA_PROVEEDOR_ID),
  KEY FACTURA_PROVEEDOR_N1 (PROVEEDOR_ID),
  CONSTRAINT `0_49` FOREIGN KEY (`FACTURA_PROVEEDOR_ID`) REFERENCES `factura` (`FACTURA_ID`),
  CONSTRAINT `0_51` FOREIGN KEY (`PROVEEDOR_ID`) REFERENCES `proveedor` (`PROVEEDOR_ID`)
) TYPE=InnoDB;


--
-- Table structure for table `item_factura`
--

DROP TABLE IF EXISTS item_factura;
CREATE TABLE item_factura (
  ITEM_FACTURA_ID bigint(20) NOT NULL default '0',
  CANTIDAD int(11) NOT NULL default '0',
  FACTURA_ID bigint(20) default NULL,
  ID bigint(20) default NULL,
  PR_TOTAL double NOT NULL default '0',
  PR_UNIT double NOT NULL default '0',
  PRODUCTO_ID bigint(20) default NULL,
  PRIMARY KEY  (ITEM_FACTURA_ID),
  KEY ITEM_FACTURA_N1 (FACTURA_ID),
  KEY ITEM_FACTURA_N2 (PRODUCTO_ID),
  CONSTRAINT `0_45` FOREIGN KEY (`PRODUCTO_ID`) REFERENCES `producto` (`PRODUCTO_ID`),
  CONSTRAINT `0_47` FOREIGN KEY (`FACTURA_ID`) REFERENCES `factura` (`FACTURA_ID`)
) TYPE=InnoDB;

--
-- Table structure for table `movimiento_caja`
--

DROP TABLE IF EXISTS movimiento_caja;
CREATE TABLE movimiento_caja (
  MOVIMIENTO_CAJA_ID bigint(20) NOT NULL default '0',
  CON_FACTURA tinyint(1) NOT NULL default '0',
  DESCRIPCION varchar(255) binary default NULL,
  FACTURA_ID bigint(20) default NULL,
  FECHA_MC datetime default NULL,
  ID bigint(20) default NULL,
  IMPORTE double NOT NULL default '0',
  NRO_RECIBO bigint(20) default NULL,
  PLANILLA_ID bigint(20) default NULL,
  TIPO_CAJA_REGISTRADO varchar(255) binary default NULL,
  TIPO_MOVIMIENTO int(11) NOT NULL default '0',
  PERIODO varchar(255) binary default NULL,
  PRIMARY KEY  (MOVIMIENTO_CAJA_ID),
  KEY MOVIMIENTO_CAJA_N2 (PLANILLA_ID),
  KEY MOVIMIENTO_CAJA_N1 (FACTURA_ID),
  CONSTRAINT `0_53` FOREIGN KEY (`PLANILLA_ID`) REFERENCES `planilla_es` (`PLANILLA_ES_ID`),
  CONSTRAINT `0_55` FOREIGN KEY (`FACTURA_ID`) REFERENCES `factura_proveedor` (`FACTURA_PROVEEDOR_ID`)
) TYPE=InnoDB;


--
-- Table structure for table `usuario_planilla_es`
--

DROP TABLE IF EXISTS usuario_planilla_es;
CREATE TABLE usuario_planilla_es (
  USUARIO_PLANILLA_ES_ID bigint(20) NOT NULL default '0',
  ID bigint(20) default NULL,
  PLANILLA_ES_ID bigint(20) default NULL,
  USUARIO_ID bigint(20) default NULL,
  PRIMARY KEY  (USUARIO_PLANILLA_ES_ID),
  KEY USUARIO_PLANILLA_ES_N1 (PLANILLA_ES_ID),
  KEY USUARIO_PLANILLA_ES_N2 (USUARIO_ID),
  CONSTRAINT `0_57` FOREIGN KEY (`PLANILLA_ES_ID`) REFERENCES `planilla_es` (`PLANILLA_ES_ID`),
  CONSTRAINT `0_59` FOREIGN KEY (`USUARIO_ID`) REFERENCES `usuario` (`USUARIO_ID`)
) TYPE=InnoDB;


--
-- Table structure for table `sesion`
--

DROP TABLE IF EXISTS sesion;
CREATE TABLE sesion (
  SESION_ID bigint(20) NOT NULL default '0',
  CIERRE datetime default NULL,
  ID bigint(20) default NULL,
  INICIO datetime default NULL,
  USUARIO_ID bigint(20) default NULL,
  PRIMARY KEY  (SESION_ID),
  KEY SESION_N1 (USUARIO_ID),
  CONSTRAINT `0_39` FOREIGN KEY (`USUARIO_ID`) REFERENCES `usuario` (`USUARIO_ID`)
) TYPE=InnoDB;


--
-- Table structure for table `beca_asignada`
--

DROP TABLE IF EXISTS beca_asignada;
CREATE TABLE beca_asignada (
  BECA_ASIGNADA_ID bigint(20) NOT NULL default '0',
  CIERRE_PLAZO tinyint(1) NOT NULL default '0',
  FECHA datetime default NULL,
  ID bigint(20) default NULL,
  IMPORTE_ASIGNADO double NOT NULL default '0',
  IMPORTE_RESTANTE double NOT NULL default '0',
  USUARIO_ID bigint(20) default NULL,
  PRIMARY KEY  (BECA_ASIGNADA_ID),
  KEY BECA_ASIGNADA_N1 (USUARIO_ID),
  CONSTRAINT `0_35` FOREIGN KEY (`USUARIO_ID`) REFERENCES `usuario` (`USUARIO_ID`)
) TYPE=InnoDB;


