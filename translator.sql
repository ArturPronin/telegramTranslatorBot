PGDMP                 	         |         
   translator    12.4    12.4 !    *           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            +           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            ,           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            -           1262    72094 
   translator    DATABASE     �   CREATE DATABASE translator WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Russian_Russia.1251' LC_CTYPE = 'Russian_Russia.1251';
    DROP DATABASE translator;
                postgres    false            �            1259    72123    ads    TABLE     }   CREATE TABLE public.ads (
    id integer NOT NULL,
    bodyads character varying(1024),
    photo character varying(1024)
);
    DROP TABLE public.ads;
       public         heap    postgres    false            �            1259    72121 
   ads_id_seq    SEQUENCE     �   CREATE SEQUENCE public.ads_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.ads_id_seq;
       public          postgres    false    203            .           0    0 
   ads_id_seq    SEQUENCE OWNED BY     9   ALTER SEQUENCE public.ads_id_seq OWNED BY public.ads.id;
          public          postgres    false    202            �            1259    72144    keyboard    TABLE     �   CREATE TABLE public.keyboard (
    id integer NOT NULL,
    keys character varying(1024),
    namekey character varying(1024)
);
    DROP TABLE public.keyboard;
       public         heap    postgres    false            �            1259    72142    keyboard_id_seq    SEQUENCE     �   CREATE SEQUENCE public.keyboard_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.keyboard_id_seq;
       public          postgres    false    207            /           0    0    keyboard_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.keyboard_id_seq OWNED BY public.keyboard.id;
          public          postgres    false    206            �            1259    72155    last_bot_message    TABLE     l   CREATE TABLE public.last_bot_message (
    id integer NOT NULL,
    last_message character varying(8192)
);
 $   DROP TABLE public.last_bot_message;
       public         heap    postgres    false            �            1259    72153    last_bot_message_id_seq    SEQUENCE     �   CREATE SEQUENCE public.last_bot_message_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.last_bot_message_id_seq;
       public          postgres    false    209            0           0    0    last_bot_message_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.last_bot_message_id_seq OWNED BY public.last_bot_message.id;
          public          postgres    false    208            �            1259    72134    users    TABLE     �   CREATE TABLE public.users (
    id integer NOT NULL,
    chatid bigint,
    username character varying(100),
    source character varying(10),
    target character varying(10)
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    72132    users_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    205            1           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    204            �
           2604    72126    ads id    DEFAULT     `   ALTER TABLE ONLY public.ads ALTER COLUMN id SET DEFAULT nextval('public.ads_id_seq'::regclass);
 5   ALTER TABLE public.ads ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    203    202    203            �
           2604    72147    keyboard id    DEFAULT     j   ALTER TABLE ONLY public.keyboard ALTER COLUMN id SET DEFAULT nextval('public.keyboard_id_seq'::regclass);
 :   ALTER TABLE public.keyboard ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    207    206    207            �
           2604    72158    last_bot_message id    DEFAULT     z   ALTER TABLE ONLY public.last_bot_message ALTER COLUMN id SET DEFAULT nextval('public.last_bot_message_id_seq'::regclass);
 B   ALTER TABLE public.last_bot_message ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    209    208    209            �
           2604    72137    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    204    205    205            !          0    72123    ads 
   TABLE DATA           1   COPY public.ads (id, bodyads, photo) FROM stdin;
    public          postgres    false    203   �!       %          0    72144    keyboard 
   TABLE DATA           5   COPY public.keyboard (id, keys, namekey) FROM stdin;
    public          postgres    false    207   �#       '          0    72155    last_bot_message 
   TABLE DATA           <   COPY public.last_bot_message (id, last_message) FROM stdin;
    public          postgres    false    209   $       #          0    72134    users 
   TABLE DATA           E   COPY public.users (id, chatid, username, source, target) FROM stdin;
    public          postgres    false    205   ($       2           0    0 
   ads_id_seq    SEQUENCE SET     8   SELECT pg_catalog.setval('public.ads_id_seq', 1, true);
          public          postgres    false    202            3           0    0    keyboard_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.keyboard_id_seq', 3, true);
          public          postgres    false    206            4           0    0    last_bot_message_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.last_bot_message_id_seq', 1, true);
          public          postgres    false    208            5           0    0    users_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.users_id_seq', 392, true);
          public          postgres    false    204            �
           2606    72131    ads ads_pkey 
   CONSTRAINT     J   ALTER TABLE ONLY public.ads
    ADD CONSTRAINT ads_pkey PRIMARY KEY (id);
 6   ALTER TABLE ONLY public.ads DROP CONSTRAINT ads_pkey;
       public            postgres    false    203            �
           2606    72152    keyboard keyboard_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.keyboard
    ADD CONSTRAINT keyboard_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.keyboard DROP CONSTRAINT keyboard_pkey;
       public            postgres    false    207            �
           2606    72163 &   last_bot_message last_bot_message_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.last_bot_message
    ADD CONSTRAINT last_bot_message_pkey PRIMARY KEY (id);
 P   ALTER TABLE ONLY public.last_bot_message DROP CONSTRAINT last_bot_message_pkey;
       public            postgres    false    209            �
           2606    72141    users users_chatid_key 
   CONSTRAINT     S   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_chatid_key UNIQUE (chatid);
 @   ALTER TABLE ONLY public.users DROP CONSTRAINT users_chatid_key;
       public            postgres    false    205            �
           2606    72139    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    205            !   �  x��RKN�@]�S�`�f�����bg	%����Ql�N E� bfn`L:q�Pu G�U�`�/��U��z�?�*����>?X)>"��s�)O8%Zd���$�^���?7/��x&�'HΌgܗ.;k)�Qe��9� �@iKGzĹ\�=r#�6D�x�S�E��*���2�T.I�F2ER�G�qf�v#�f
J �<#i�9Ȼ�iv��Re��yF��Z<����d���j�\��a���
{��M��jAtT�;��Z+���������w�)@�9f&���v_����u�ֲps�����p�boS���2���K�q�;�/�qf�͚ؔ�-�Qgኜ�j������ȹ\Pa��oLN�d��:��Т@��ݵw`Ԝ�i�V�_z�(,�����v�hE�1��f#:�æ���p��+*4wK~�T*�Ч      %   m   x�3��())(���/��M�O-�M,*I���.�1�@L��'�R�2DU[R��W��X��T�ya�}�.쿰�bÅ�6]�wa���;.�Rx�5���~�=... 9y5*      '      x�3��H�������� 1�      #   Y   x�U�1� @ѹ���m�..$� �J��^���^Pt�y	��uV�`��D�H��!��l�Z�y~��鯯	r��G�5�     