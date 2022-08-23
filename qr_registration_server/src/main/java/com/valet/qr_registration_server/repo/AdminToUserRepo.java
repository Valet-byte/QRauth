package com.valet.qr_registration_server.repo;

import com.valet.qr_registration_server.model.AdminToUser;
import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface AdminToUserRepo extends ReactiveCrudRepository<AdminToUser, Long> {
    @Modifying
    @Query("DELETE from admin_user WHERE admin_id = :id OR user_id = :id")
    Mono<Void> deleteAdminToUserByAdminIdOrUserId(@Param("id") long id);

    @Modifying
    @Query("DELETE from admin_user WHERE user_id = any(:ids) OR admin_id = any(:ids)")
    Mono<Void> deleteAllByIds(@Param("ids") Publisher<Long> ids);

}
